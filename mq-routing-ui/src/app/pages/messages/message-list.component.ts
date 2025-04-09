import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';

import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

import { Message } from '../../models/message.model';
import { HttpClientModule } from '@angular/common/http';

import { MessageService } from '../../services/messages.service';
import { MessageDetailsComponent } from './message-details/message-details.component';

@Component({
  selector: 'app-message-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatDividerModule,
    MatTableModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    MessageService
  ],
  templateUrl: './message-list.component.html',
  styleUrl: './message-list.component.scss',
})
export class MessageListComponent implements OnInit {

  currentPage: number = 0;
  pageSize: number = 10; 
  columns = [
    'id',
    'content', 
    'createdAt', 
    'action'
  ];
    resultsLength: number = 0;

  dataSource = new MatTableDataSource<Message>([]);

  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;

  constructor(private dialog: MatDialog,
    private messageService: MessageService 
  ) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAllMessages();
  }


  viewDetails(message: Message) {

    this.dialog.open(MessageDetailsComponent, {
      width: '80%',
      data: message
    });
  }

  paginationChnage(e: any) {
    this.currentPage = e.pageIndex;
    this.getAllMessages();
  }

  getAllMessages() {
    this.messageService.getMessages(this.currentPage, this.pageSize).subscribe(page => {
        this.dataSource = new MatTableDataSource<Message>(page.content);
        this.resultsLength = page.totalElements;
      });
  }

}
