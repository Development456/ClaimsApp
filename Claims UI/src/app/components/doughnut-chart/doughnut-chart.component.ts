import { Component, Input, OnInit } from '@angular/core';

import { ClaimsApiService } from 'src/app/claims-api.service';
import { Color } from 'ng2-charts';

@Component({
  selector: 'app-doughnut-chart',
  templateUrl: './doughnut-chart.component.html',
  styleUrls: ['./doughnut-chart.component.css']
})
export class DoughnutChartComponent implements OnInit {
  @Input() claimData: any[] = [];
  @Input() set facilityId(id: string) {
    this.facilityChange = id;
    this.facilityCheck();
  };
  facilityChange: string = '';
  public doughnutChartLabels: any[] = [];
  public doughnutChartData: any[] = [];
  public doughnutChartType: any = 'doughnut';
  public doughnutOptions: any = {
    segmentShowStroke: false,
    animateScale: true,
    centerText: {
      display: true,
      text: "280"
    },
    legend: {
      position: 'right'
    }
  };
  public doughnutChartColor: Color[] = [
    { backgroundColor: ['#FF9021', '#4BC0C0', '#36A2EB', '#FF6484', '#13FFFF', '#64FF16', '#FFA3B5', '#FFC898', '#FFE0A1', '#A0D0F5', '#9966FF'] },
  ];
  isLoading: boolean = false;

  constructor(private http: ClaimsApiService) { }

  facilityCheck(): void {
    if (this.facilityChange) {
      this.isLoading = true;
      this.http.getClaimByFacility(this.facilityChange).subscribe((data:any) => {
        this.isLoading = false;
        this.doughnutChartLabels = [];
      this.doughnutChartData = [];
      let ele: any = {};
      data.map((item: any) => {
        if (ele[item.claimStatus]) {
          ele[item.claimStatus] += 1;
        } else {
          ele[item.claimStatus] = 1;
        }
      })
      let values = Object.entries(ele);
      values.forEach((status: any) => {
        this.doughnutChartLabels.push(status[0] + ' - ' + status[1]);
        this.doughnutChartData.push(status[1]);
      })
      })

    } else {
      this.ngOnInit();
    }
  }
  ngOnInit(): void {
    this.isLoading = true;
    this.http.getClaims().subscribe((data: any) => {
      this.isLoading = false;
      this.doughnutChartLabels = [];
      this.doughnutChartData = [];
      let ele: any = {};
      data.map((item: any) => {
        if (ele[item.claimStatus]) {
          ele[item.claimStatus] += 1;
        } else {
          ele[item.claimStatus] = 1;
        }
      })
      let values = Object.entries(ele);
      values.forEach((status: any) => {
        this.doughnutChartLabels.push(status[0] + ' - ' + status[1]);
        this.doughnutChartData.push(status[1]);
      })
    })
  }
}
