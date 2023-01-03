import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ColumnMode, SelectionType } from '@swimlane/ngx-datatable';
import { Subscription } from 'rxjs';
import { ClaimsApiService } from 'src/app/claims-api.service';
import { Claim } from '../model/claim.model';

@Component({
  selector: 'app-claims-details',
  templateUrl: './claims-details.component.html',
  styleUrls: ['./claims-details.component.css']
})
export class ClaimsDetailsComponent implements OnInit, OnDestroy {
  ordersList: any = [];
  filteredColumns: any = [];
  ColumnMode = ColumnMode;
  SelectionType = SelectionType;
  claimAmount: any = {};
  claimData: any = {};
  userMode: string | null = 'user';
  firstFormGroup!: FormGroup;
  contactInformation!: FormGroup;
  costDetails!: FormGroup;
  paymentInformation!: FormGroup;
  additionalInfo!: FormGroup;
  claimsUpdatedData = {} as Claim ;
  facilityList: any = [];
  customerList: string[] = [];
  updateCalims: Subscription = new Subscription();
  isLoading: boolean = false;

  constructor(public dialogRef: MatDialogRef<ClaimsDetailsComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, 
              private _formBuilder: FormBuilder, 
              private http: ClaimsApiService, 
              private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.initializeForm();
    this.isLoading = true;
    this.userMode = localStorage.getItem('userDetails') ? localStorage.getItem('userDetails') : 'user';
    this.http.getFacility().subscribe((data: any) => {
      this.isLoading = false;
      this.facilityList = data;
    });

    setTimeout(() => {
      this.ordersList = this.data.orders;
    }, 500)
    this.filteredColumns = [{ "name": "Item", "props": "item", width: 60 }, 
                            { "name": "Description", props: "des" }, 
                            { "name": "Date Code", props: "dateCode" },
                            { "name": "LOT", props: "lot" }, 
                            { "name": "Quantity", props: "quantity" },
                            { "name": "LPN", props: "LPN" },
                            { "name": "NET", props: "NET" }];

    this.firstFormGroup.controls['customerClaim'].disable()
  }

  initializeForm() {
    this.firstFormGroup = this._formBuilder.group({
      createdDate: [new Date(this.data.rowData.creationDate), Validators.required],
      closedDate: [new Date(this.data.rowData.dateClosed), Validators.required],
      customerClaim: [this.data.rowData.serviceProviderClaimId],
      customer: ['', Validators.required],
      facility: [this.data.rowData.facilityId, Validators.required],
      wmsAccount: ['123', Validators.required],
      claimType: [this.data.rowData.claimType.toLowerCase().charAt(0).toUpperCase() + this.data.rowData.claimType.toLowerCase().slice(1), Validators.required],
      claimCategory: [this.data.rowData.category, Validators.required],
      status: [this.data.rowData.claimStatus, Validators.required],
      priorityFlag: ['Low', Validators.required],
      commonType: ['Low', Validators.required],
      issueType: ['Low', Validators.required],
    });

    this.contactInformation = this._formBuilder.group({
      name: ['admin', Validators.required],
      phone: ['1111111111', Validators.required],
      email: ['admin@miracle', Validators.required],

    });

    this.costDetails = this._formBuilder.group({
      amountBasis: ['Product', Validators.required],
      cost: [this.data.rowData.claimedAmount.slice(1), Validators.required],
      currency: ['USD', Validators.required],

    });

    this.paymentInformation = this._formBuilder.group({
      apVendor: ['Vendor', Validators.required], 
      paidAmount: ['27', Validators.required],
      paymentReference: ['789Sd', Validators.required], 
      paymentDate: ['12/19/2022', Validators.required],
      invoiceNumber: ['123', Validators.required], 
      costCenter: ['Billing team', Validators.required],
      glCode: ['78usd'], 
      accuralAmount: ['49', Validators.required],
      invoiceAmount: ['49'], 
      claimedAmount: [27, Validators.required],
      currencyType: ['USD', Validators.required],

    });

    this.additionalInfo = this._formBuilder.group({
      notes: [''], 
      document: ['']
    });
  }

  saveClaimDetails() {
    this.editServiceCall(this.firstFormGroup.value, this.costDetails.value);
    this.isLoading = true;
    this.updateCalims = this.http.updateClaim(this.claimsUpdatedData, this.data.rowData.serviceProviderClaimId).subscribe((data)=>{
      console.log('Edit succesful!!')
      this.isLoading = false;
      this._snackBar.open("Progress Saved", "Close", {  duration: 5000 });
      this.dialogRef.close({data: this.claimsUpdatedData});
    }, 
    (error) => {
      this.isLoading = false;
      this._snackBar.open("Error while Editing", "Close", {  duration: 3000 });
    });
  }

  editServiceCall(firstFormGroup: any, costDetails: any) {
    this.claimsUpdatedData._id = this.data.rowData._id;
    this.claimsUpdatedData.createDate = this.formatDate(firstFormGroup.createdDate);
    this.claimsUpdatedData.closedDate = this.formatDate(firstFormGroup.closedDate);
    this.claimsUpdatedData.claimId= this.data.rowData.claimId.trim();
    this.claimsUpdatedData.facilityId = firstFormGroup.facility;
    this.claimsUpdatedData.palletQuantity = this.data.rowData.palletQuantity;
    this.claimsUpdatedData.documentType = this.data.rowData.documentType;
    this.claimsUpdatedData.claimedAmount = costDetails.cost.toString();
    this.claimsUpdatedData.serviceProviderClaimId = firstFormGroup.customerClaim;
    this.claimsUpdatedData.claimStatus = firstFormGroup.status;
    this.claimsUpdatedData.claimType = firstFormGroup.claimType.toUpperCase();
    this.claimsUpdatedData.creatorId = '';
    this.claimsUpdatedData.lastUpdateId = this.data.rowData.lastUpdateId.trim();
    this.claimsUpdatedData.lastUpdateDate = this.data.rowData.lastUpdateDate.trim();
    return this.claimsUpdatedData;
  }

  ngOnDestroy(): void {
    if(this.updateCalims) {
      this.updateCalims.unsubscribe();
    }
  }

  addQuantity(e: any, row: any) {
    console.log(row);
    this.claimAmount[row.item] = e.target.value * 5;
    let total = 0;
    for (let amount of Object.keys(this.claimAmount)) {
      total += this.claimAmount[amount];
    }
    this.costDetails.setValue({
      amountBasis: this.costDetails.value.amountBasis ? this.costDetails.value.amountBasis : 'Product',
      cost: total,
      currency: this.costDetails.value.currency ? this.costDetails.value.currency : 'USD'
    })
    this.paymentInformation.controls['claimedAmount'].setValue(total);
    this.paymentInformation.controls['currencyType'].setValue(this.costDetails.value.currency ? this.costDetails.value.currency : 'USD');
  }

  formatDate(date: any) {
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [month, day, year].join('/');
  }
}
