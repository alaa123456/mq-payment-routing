import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Partner } from '../models/partner.model';
import { Page } from '../models/page.model';


@Injectable({
  providedIn: 'root'
})
export class PartnerService {
  SERVER_URL_API = `http://localhost:8080/api/v1`;


  constructor(private httpClient: HttpClient) {}

  deletePartner(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.SERVER_URL_API}/partners/${id}`);
  }


  getAllPartners(page: number, size: number): Observable<Page<Partner>> {
    return this.httpClient.get<Page<Partner>>(`${this.SERVER_URL_API}/partners?page=${page}&size=${size}`);
  }

  addNewPartner(partner: Partner): Observable<Partner> {
    return this.httpClient.post<Partner>(this.SERVER_URL_API+"/partners", partner);
  }

}