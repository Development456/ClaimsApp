import { Component, OnInit, ViewChild } from '@angular/core';

import { ClaimsMockData } from '../mock-data/claims-list-constant';
import { MatDrawer } from '@angular/material/sidenav';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  showFiller = true;
  @ViewChild(MatDrawer) drawer: any;
  navOptions = "home";
  years: any
  public claims: any = [];
  public openClaims: any[] = [];
  public closedClaims: any[] = [];
  public statusData: any = {};
  public openBarChartColor = '#36A2EB';
  public closedBarChartColor = '#FF6484';
  public openSize = 3000;
  public closedSize = 50000;
  selectedDataItems = [];
  tempData: any = []
  show = true;
  selectedDay: any;
  notifyObj = new Notifier();
  facilityId:string='';
  constructor() { }

  ngOnInit(): void {
    this.claims = ClaimsMockData;
    this.tempData = this.claims
    this.openClaims = this.claims;
    this.closedClaims = this.claims;

  }

  facilityChange(facilityId: string) {
    this.facilityId = facilityId;
  }

  yearrange(event: any) {
    this.selectedDay = {
      value: event.value,
      text: event.source.triggerValue
    };
    this.claims = [].concat(this.tempData.filter((x: any) => {
      let event12 = new Date(x.date);
      if (event12.getFullYear() == this.selectedDay.text) {
        return true;
      } else {
        return false;
      }
    }))
    this.notifyObj.valueChanged(this.claims);
  }
  selectedData(e: any) {
    this.selectedDataItems = e;
    this.navOptions = 'addClaim';
  }
  getYear(e: any) {
    this.show = false;
    this.years = Number(e.value);
    this.ngOnInit();

    setTimeout(() => {
      this.claims = this.claims.filter((data: any) => {
        let event = new Date(data.date);
        if (event.getFullYear() == this.years) {
          return true;
        } else {
          return false;
        }

      })
      this.show = true
    }, 0)
  }
}

export class Notifier {
  valueChanged: (data: any) => void = (data: any) => { };
}
