import { OrderList } from './components/mock-data/order-list.constant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClaimsApiService {

  ordersList = OrderList;

  constructor(private http: HttpClient) { }

  getFacility() {
    return this.http.get(environment.FACILITY_URL + `/facility`);
  }

  getCustomer() {
    return this.http.get(environment.CUSTOMER_URL +`/customer`);
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
    return this.http.get(environment.CLAIMS_URL + '/claims/getclaims');
  }

  getClaimsById(id: string) {
    return this.http.get(environment.CLAIMS_URL + `/claims/${id}`);
  }

  getClaimByFacility(id: string) {
    if (id) {
      return this.http.get(environment.CLAIMS_URL + `/claims/facility/${id}`);
    } else {
      return this.http.get(environment.CLAIMS_URL + `/claims/getclaims`);
    }
  }

  createClaim(claim: Object) {
    const headers= new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*');

    return this.http.post(environment.CLAIMS_URL +'/claims', claim,{headers});
  }

  updateClaim(editedCalimsBody: any, serviceProviderId: number) {
    const url = environment.CLAIMS_URL +`/claims/${serviceProviderId}`;
    return this.http.put<any>(url, editedCalimsBody); 
  }
}
