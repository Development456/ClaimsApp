import { ChartOptions, ChartType } from 'chart.js';
import { Component, Input, OnInit } from '@angular/core';

import { ClaimsApiService } from 'src/app/claims-api.service';
import { Color } from 'ng2-charts';

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css']
})
export class BarChartComponent implements OnInit {
  @Input() claimData: any[] = [];
  @Input() barchartColor: any;
  @Input() barSize: any;
  @Input() set facilityId(id: string) {
    this.facilityChange = id;
    this.facilityCheck();
  };
  barchartFlag = true;
  facilityChange: string = '';
  public openClaims: any[] = [];
  public barChartOptions: ChartOptions = {
    responsive: true,
    // maintainAspectRatio: false,
    animation: {
      animateScale: true
    },
    tooltips: {
      callbacks: {
        label: (e) => {
          return '$ ' + e.value;
        }
      }
    },
    scales: {

      xAxes: [
        {
          stacked: false,
          gridLines: {
            display: false
          }
        }
      ],
      yAxes: [
        {
          stacked: false,
          ticks: {
            // Include a dollar sign in the ticks
            callback: function (value, index, ticks) {
              return '$' + value;
            }
          }
        }
      ]
    }
  };

  public barChartData: any = [
    { data: [] }
  ];

  public barChartLabels: any = [];

  public barChartType: ChartType = 'bar';

  public barChartLegends = false;

  public barChartColor: Color[] = [
    { backgroundColor: ['#36A2EB', '#4BC0C0', '#FF6484', '#13FFFF', '#64FF16', '#36A2EB', '#4BC0C0', '#FF6484', '#13FFFF', '#64FF16', '#36A2EB', '#4BC0C0', '#FF6484', '#13FFFF', '#64FF16', '#36A2EB', '#4BC0C0', '#FF6484', '#13FFFF', '#64FF16'] },
  ];
  isLoading: boolean = false;

  constructor(private http: ClaimsApiService) { }

  facilityCheck() {
    this.barchartFlag = false;
    this.barChartData[0].data = [];
    this.barChartLabels = [];
    this.isLoading = true;
    if (this.facilityChange) {
      this.http.getClaimByFacility(this.facilityChange).subscribe((data: any) => {
        this.isLoading = false;
        let amount: any = [];
        data.forEach((claim: any, index: number) => {
          this.barChartLabels.push(this.claimData[index]?.masterAcct);
          amount.push(Math.floor(Number(claim.claimedAmount.toString().replace(',', ''))));
        })
        amount.sort((a: number, b: number) => {
          return b - a
        }).forEach((item: number) => {
          this.barChartData[0].data.push(item);

        });

        this.barChartLabels = this.barChartLabels.slice(0, 5);
        this.barchartFlag = true;

      });
    } else {
      this.loadBarchart();
    }
  }

  loadBarchart() {
    this.barchartFlag = false;
    this.barChartData[0].data = [];
    this.barChartLabels = [];
    this.isLoading = true;
    this.http.getClaims().subscribe((data: any) => {
      this.isLoading = false;
      let amount: any = [];
      data.forEach((claim: any, index: number) => {
        this.barChartLabels.push(this.claimData[index]?.masterAcct);
        amount.push(Math.floor(Number(claim.claimedAmount.toString().replace(',', ''))));
      })
      amount.sort((a: number, b: number) => {
        return b - a
      }).forEach((item: number) => {
        this.barChartData[0].data.push(item);
      });

      this.barChartLabels = this.barChartLabels.slice(0, 5);
      this.barchartFlag = true;
    })
  }

  ngOnInit(): void {
    this.facilityCheck();
  }
}
