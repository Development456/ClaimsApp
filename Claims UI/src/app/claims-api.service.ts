import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Injectable } from '@angular/core';
import { OrderList } from './components/mock-data/order-list.constant';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClaimsApiService {

  ordersList = OrderList;

  constructor(private http: HttpClient) { }

  getFacility() {
    return this.http.get(environment.URL + `/facility`);
  }

  getCustomer() {
    return this.http.get(`http://localhost:8400/customer`);
  }

  getCustomerReference() {
    return this.ordersList.map(item => {
      return item.customerReference;
    })
  }
  getAMCReference() {
    return this.ordersList.map(item => {
      return item.AMCRefenrence;
    })
  }
  getOrders() {
    return this.ordersList;
  }

  getClaims() {
    return this.http.get('http://localhost:8100/claims');
  }

  getClaimsById(id: string) {
    return this.http.get( `http://localhost:8100/claims/${id}`);
  }

  getClaimByFacility(id: string) {
    if (id) {
      return this.http.get( `http://localhost:8100/claims/facility/${id}`);
    } else {
      return this.http.get( `http://localhost:8100/claims`);
    }
  }

  createClaim(claim: Object) {
    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*');

    return this.http.post('http://localhost:8100/claims', claim,{headers});
  }

  updateClaim(editedCalimsBody: any, serviceProviderId: number) {
    const url = `http://localhost:8100/claims/${serviceProviderId}`;
    return this.http.put<any>(url, editedCalimsBody); 
  }
}
