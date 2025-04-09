
import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Message } from '../../../models/message.model';

import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';



@Component({
  selector: 'app-message-dialog',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,

    MatPaginatorModule,
    MatIconModule,
    MatCardModule,
    MatDividerModule,

    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatButtonModule,
    MatTableModule,
    MatSelectModule,

    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './message-details.component.html',
  styleUrl: './message-details.component.scss'
})



export class MessageDetailsComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Message) {}
}
