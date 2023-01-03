import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

import { ClaimsApiService } from 'src/app/claims-api.service';
import { Notifier } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-data-cards',
  templateUrl: './data-cards.component.html',
  styleUrls: ['./data-cards.component.css']
})
export class DataCardsComponent implements OnInit {
  @Input() rowData: any[] = [];
  @Input() notify = new Notifier();
  @Output() year: any = new EventEmitter();
  @Output() facilityChange: any = new EventEmitter();
  public facilities: any = [];
  public customers: any = [];
  public claims: any;
  public claimAmount = 0;
  public paidAmount = 0;
  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });
  selectedData: any;
  facility: any = [];
  facilityData: any;
  masterData: any = [];
  count: any;
  uniqueChars: any = [];
  claimed: any = [];
  paied: any = [];
  amountdata: any = [];
  amountdata1: any = [];
  totclaimamount: any = [];
  totpaidamount: any = [];
  Wearhouse: any;
  isFacilityLoading: boolean = false;
  isClaimsLoading: boolean = false;
  isCustomerLoading: boolean = false;

  constructor(private http: ClaimsApiService) { }

  ngOnInit(): void {
    this.isFacilityLoading = true;
    this.isClaimsLoading = true;
    this.isCustomerLoading = true;
    this.getFacility();
    this.getClaims('');
    this.getCustomer();
    this.initFilter(this.rowData);
    this.Wearhouse = []
  }
  getFacility() {
    this.http.getFacility().subscribe((data) => {
      this.isFacilityLoading = false;
      this.facilities = data;
    },
    (error) => {
      this.isFacilityLoading = true;
    }
    )
  }
  getCustomer(){
    this.http.getCustomer().subscribe(data => {
      this.isCustomerLoading = false;
      this.customers = data;
    },
    (error) => {
      this.isCustomerLoading = true;
    }
    )
  }

  getClaims(facilityId: string) {
    
    this.claims = 0;
    this.claimAmount = 0;
    this.paidAmount = 0;
    this.http.getClaimByFacility(facilityId).subscribe((data: any) => {
      this.isClaimsLoading = false;
      if(!facilityId){
        this.getCustomer();
      }
      if (!data.length) {
        this.customers = 1;
        this.claims = 1;
        this.claimAmount += Number(data.claimedAmount.replace(',', ''));
        this.paidAmount += Number(data.claimedAmount.replace(',', ''));
      } else {
        this.claims = data.length;
        this.customers = data;
        data.forEach((element: any) => {
          this.claimAmount += Number(element.claimedAmount.replace(',', ''));
          this.paidAmount += Number(element.claimedAmount.replace(',', ''));
        });
      }

    },
    (error) => {
      this.isClaimsLoading = true;
    });

      this.facilityChange.emit(facilityId);
  }

  ngAfterViewInit() {
    this.notify.valueChanged = (data: any) => {
      this.initFilter(data)
    };
  }
  public initFilter(data: any): void {
    this.rowData = data;

    // this.facilities = Array.from(new Set(this.rowData.map(({ facility }) => facility)));
    // this.customers = Array.from(new Set(this.rowData.map(({ masterAcct }) => masterAcct)));
    // this.claimAmount = 0;
    this.paidAmount = 0;
    this.rowData.forEach(element => {
      let amount = typeof element.claimedAmount === 'string' ? +element.claimedAmount.substring(1) : element.claimedAmount;
      let paidAmount = +element.paidAmount.substring(1);
      // this.claimAmount += amount ? amount : 0;
      this.paidAmount += paidAmount ? paidAmount : 0;
    });
  }

  changeFacility(e: any) {
    this.getClaims(e.value);
  }

}
