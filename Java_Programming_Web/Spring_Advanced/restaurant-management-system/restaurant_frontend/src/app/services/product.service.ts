import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) {}

  add(data: any) {
    return this.httpClient.post(this.url + '/products/add', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  update(data: any) {
    return this.httpClient.post(this.url + '/products/update', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  getProducts() {
    return this.httpClient.get(this.url + '/products/get');
  }

  updateStatus(data: any) {
    return this.httpClient.patch(this.url + '/products/updateStatus', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  delete(id: any) {
    return this.httpClient.delete(this.url + '/products/delete/' + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json'),
    });
  }

  getProductsByCategory(id: any) {
    return this.httpClient.get(this.url + "/products/getByCategory/" + id);
  }

  getProductsById(id: any) {
    return this.httpClient.get(this.url + "/products/getById/" + id);
  }



}
