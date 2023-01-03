import { Component, DoCheck, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatBottomSheet, MatBottomSheetRef } from '@angular/material/bottom-sheet';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ColumnMode } from '@swimlane/ngx-datatable';
import { ClaimsApiService } from 'src/app/claims-api.service';
import { ClaimsDetailsComponent } from '../claims-details/claims-details.component';
import { DetailsModalComponent } from '../data-table/details-modal/details-modal.component';

@Component({
  selector: 'app-add-claims',
  templateUrl: './add-claims.component.html',
  styleUrls: ['./add-claims.component.css']
})
export class AddClaimComponent implements OnInit,DoCheck {
  @Input() set selectedDataItems(item: any) {
    this.ordersDataItems = item;
    // if (item.length > 0) {
    //   this.firstFormGroup.setValue({
    //     createdDate: item[0].date,
    //     // closedDate: item[0].dateClosed,
    //     customerClaim: item[0].claimedAmount,
    //     customer: item[0].date,
    //     facility: item[0].facility,
    //     // wmsAccount: item[0].masterAcct, claimType: item[0].claimType,
    //     // claimCategory: item[0].category,
    //     // status: item[0].status, priorityFlag: '',
    //     // commonType: '',
    //     // issueType: '',
    //   })
    // }

  }
  ordersDataItems:any=[];
  spinner = false;

  firstFormGroup = this._formBuilder.group({
    createdDate: [new Date(), Validators.required],
    // closedDate: ['', Validators.required],
    customerClaim: [null],
    customer: ['', Validators.required],
    facility: ['', Validators.required],
    name: ['admin', Validators.required],
    phone: ['1111111111', Validators.required],
    email: ['admin@miracle', Validators.required],
    amcReference: ['', Validators.required],
    customerReference: ['', Validators.required],
    documentType: ['', Validators.required]
  });
  secondFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', Validators.required],
    notes: ['', Validators.required]
  });
  thirdFormGroup = this._formBuilder.group({
    apVendor: ['', Validators.required], paidAmount: ['', Validators.required],
    paymentReference: ['', Validators.required], paymentDate: ['', Validators.required],
    invoiceNumber: ['', Validators.required], costCenter: ['', Validators.required],
    glCode: ['', Validators.required], accuralAmount: ['', Validators.required],
    invoiceAmount: ['', Validators.required], claimedAmount: ['', Validators.required],
    currencyType: ['', Validators.required],

  });
  fifthFormGroup = this._formBuilder.group({
    // appointmentID: ['', Validators.required], 
    // bol: ['', Validators.required],
    // receivedDate: ['', Validators.required], 
    amcReference: ['', Validators.required],
    customerReference: ['', Validators.required],
    documentType: ['', Validators.required]
  })
  sixthFormGroup = this._formBuilder.group({
    name: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', Validators.required],

  });
  seventhFormGroup = this._formBuilder.group({
    notes: ['', Validators.required]
  });
  eightFormGroup = this._formBuilder.group({
    name: ['', Validators.required],

  })
  isLinear = false;
  facilityList: string[] = [];
  customerList: string[] = [];
  customerReference: number[] = [];
  amcReference: number[] = [];
  costDetails = this._formBuilder.group({
    amountBasis: ['Product', Validators.required],
    cost: [0, Validators.required],
    currency: ['USD', Validators.required],

  });
  additionalInfo = this._formBuilder.group({
    notes: [''], document: ['']

  });
  totalAmount:any = {};
  constructor(private _formBuilder: FormBuilder, private http: ClaimsApiService, public dialog: MatDialog,private _bottomSheet: MatBottomSheet) { }

  ngDoCheck(): void {
    let temp=0;
    Object.keys(this.totalAmount).forEach(item=>{
      temp += this.totalAmount[item];
    })
      this.costDetails.controls.cost.setValue(temp);
  }
  ngOnInit(): void {
    this.spinner = false;
    this.ordersDataItems = this.http.getOrders();
    setTimeout(() => {
      // this.facilityList = this.http.getFacility().splice(0,1);
      if(this.facilityList.length==1){
        this.firstFormGroup.controls.facility.setValue(this.facilityList[0]);
      }
      // this.customerList = this.http.getCustomer().splice(0,1);
      if(this.customerList.length==1){
        this.firstFormGroup.controls.customer.setValue(this.customerList[0]);
      }
      this.customerReference = this.http.getCustomerReference();
      this.customerReference = this.http.getCustomerReference();
      this.amcReference = this.http.getAMCReference();
      this.spinner = true;
    }, 2000);

  }
  filterData() {
    this.customerReference = this.http.getCustomerReference().filter(item => item.toString().indexOf(this.fifthFormGroup.value.customerReference ? this.fifthFormGroup.value.customerReference : '') > -1)
    this.amcReference = this.http.getAMCReference().filter(item => item.toString().indexOf(this.fifthFormGroup.value.amcReference ? this.fifthFormGroup.value.amcReference : '') > -1)
  }
  checkAllFormsvalid() {
    let result = this.fifthFormGroup.status === 'VALID' && this.fifthFormGroup.status === 'VALID';
    return result;

  }
  submitData() {
    console.log(this.ordersList);
    const confirmDialog = this.dialog.open(DialogBoxComponent, { data: { orders: this.http.getOrders() }, autoFocus: false });
    confirmDialog.afterClosed().subscribe(result => {
      // console.log(result);
      
      if (result) {
        let claim = {
          "claimId": "8",
          "facilityId": this.ordersList.formValues.facility ? this.ordersList.formValues.facility : this.ordersList.row[0].facilityId,
          "palletQuantity": this.ordersList.row[0].quantity,
          "documentType": "inbound receipt",
          "claimedAmount": this.costDetails.value.cost,
          "claimStatus": "Open",
          "claimType": "WAREHOUSE",
          "lastUpdateId": "8",
          "createDate": new Date(),
          "lastUpdateDate": "8"
          }
          console.log(claim);
        this.http.createClaim(claim).subscribe(data=>{
          location.reload();
        })
        // const dialogRef = this.dialog.open(DetailsModalComponent, { data: { orders: this.http.getOrders() }, autoFocus: false });

        // dialogRef.afterClosed().subscribe(result => {
        //   console.log(`Dialog result: ${result}`);
        // });
      }

    });

    // dialogRef.afterClosed().subscribe(result => {
    // 	console.log(`Dialog result: ${result}`);
    // });
  }
  ColumnMode = ColumnMode;

  filteredColumns = [{ "name": "Item", "props": "item", width: 60 }, { "name": "Description", props: "des" }, { "name": "Date Code", props: "dateCode" }, { "name": "LOT", props: "lot" }, { "name": "Quantity", props: "quantity" }, { "name": "LPN", props: "LPN" }, { "name": "NET", props: "NET" }];
  ordersList:any = {row:[],formValues:{}};
  listItems(items:any){
    this.ordersList = {row:[],formValues:{}};
    setTimeout(()=>{
      console.log(items);
    this.ordersList.row = items;
    console.log(this.ordersList);

    },500)
    // this.ordersList = items;
  };
  openBottomSheet(): void {
    this._bottomSheet.open(BottomSheetOverviewExampleSheet);
  }
}


@Component({
  selector: 'app-add-dialogBox',
  template: `<mat-card color="primary">
  <mat-card-header style="background-color: #36a2eb;border-radius:4px;">
    <mat-card-title style="margin-top: 2%;">Duplicate Exists</mat-card-title>
    <mat-card-subtitle>Claim with same customer reference number and amc reference number is created. Do you wish to proceed?</mat-card-subtitle>
  </mat-card-header>
  <mat-card-actions>
  <button mat-button [mat-dialog-close]="true">Yes</button>
  <button mat-button (click)=show(false)>No</button>
</mat-card-actions>
 
</mat-card>`,
  styleUrls: ['./add-claims.component.css']

})
export class DialogBoxComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogBoxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  ngOnInit(): void {

  }
  show(val: boolean) {
    this.data.result = val;

    this.dialogRef.close();
  }

}

@Component({
  selector: 'bottom-sheet-overview-example-sheet',
  template: `<mat-nav-list>
  <a href="https://keep.google.com/" mat-list-item (click)="openLink($event)">
    <span matListItemTitle>Google Keep</span>
    <span matLine>Add to a note</span>
  </a>

  <a href="https://docs.google.com/" mat-list-item (click)="openLink($event)">
    <span matListItemTitle>Google Docs</span>
    <span matLine>Embed in a document</span>
  </a>

  <a href="https://plus.google.com/" mat-list-item (click)="openLink($event)">
    <span matListItemTitle>Google Plus</span>
    <span matLine>Share with your friends</span>
  </a>

  <a href="https://hangouts.google.com/" mat-list-item (click)="openLink($event)">
    <span matListItemTitle>Google Hangouts</span>
    <span matLine>Show to your coworkers</span>
  </a>
</mat-nav-list>`,
})
export class BottomSheetOverviewExampleSheet {
  constructor(private _bottomSheetRef: MatBottomSheetRef<BottomSheetOverviewExampleSheet>) {}

  openLink(event: MouseEvent): void {
    this._bottomSheetRef.dismiss();
    event.preventDefault();
  }
}