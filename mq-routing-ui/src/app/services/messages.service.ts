import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Message } from '../models/message.model';
import { Page } from '../models/page.model';


@Injectable({
   providedIn: 'root'
})
export class MessageService {
 
  SERVER_URL_API = `http://localhost:8080/api/v1`;

  constructor(private httpClient: HttpClient) {}



  getMessageDetails(id: number): Observable<Message> {
    return this.httpClient.get<Message>(`${this.SERVER_URL_API}/messages/${id}`);
  }
  getMessages(page: number, size: number): Observable<Page<Message>> {
    return this.httpClient.get<Page<Message>>(`${this.SERVER_URL_API}/messages?page=${page}&size=${size}`);
  }
}