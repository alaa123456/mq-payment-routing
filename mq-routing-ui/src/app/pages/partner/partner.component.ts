import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDialogModule, MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { PartnerService } from '../../services/partner.service';
import { Partner } from '../../models/partner.model';
import { PartnerFormComponent } from './partner-form/partner-form.component';

import { catchError, finalize, of } from 'rxjs';

@Component({
  selector: 'app-partner',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatDividerModule,
    HttpClientModule,
    MatPaginatorModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [PartnerService],
  templateUrl: './partner.component.html',
  styleUrls: ['./partner.component.scss'],
})
export class PartnerComponent implements OnInit {
  columns = ['id', 'alias', 'type', 'direction', 'application', 'processedFlowType', 'description', 'action'];
  dataSource = new MatTableDataSource<Partner>([]);
 
  deletePartnerDialogRef: MatDialogRef<any> | null = null;
  resultsLength = 0;
  currentPage = 0;
  pageSize = 10;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild('deletePartnerModal') deletePartnerModal!: TemplateRef<any>;

  constructor(private dialog: MatDialog, private partnerService: PartnerService) {}

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getAllPartners();
  }

  getAllPartners() {
    this.partnerService.getAllPartners(this.currentPage, this.pageSize).subscribe(page => {
      this.dataSource.data = page.content;
      this.resultsLength = page.totalElements;
    });
  }

  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.getAllPartners();
  }

  openAddPartnerDialog() {
    const dialogRef = this.dialog.open(PartnerFormComponent,);
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.getAllPartners();
      }
    });
  }


  deletePartner(id: number) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '600px';
    dialogConfig.data = { message: 'Voulez-vous vraiment supprimer ce partenaire ?' };

    this.deletePartnerDialogRef = this.dialog.open(this.deletePartnerModal, dialogConfig);

    this.deletePartnerDialogRef.afterClosed().pipe(finalize(() => this.deletePartnerDialogRef = null))
      .subscribe(result => {
        if (result) {
          this.partnerService.deletePartner(id).subscribe(() => this.getAllPartners());
        }
      });
  }
}