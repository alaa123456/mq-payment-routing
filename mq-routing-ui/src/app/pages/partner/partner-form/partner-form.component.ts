import { Component, OnInit, Inject } from '@angular/core';

import { MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
} from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { PartnerService } from '../../../services/partner.service';
import { Partner } from '../../../models/partner.model';
import {
  FormBuilder,
  Validators,
  FormGroup,

  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-partner-dialog',
  standalone: true,
  imports: [
    CommonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatDividerModule,
    MatProgressSpinnerModule,

    MatButtonModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './partner-form.component.html',
  styleUrl: './partner-form.component.scss',
})
export class PartnerFormComponent implements OnInit {
  partnerForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<PartnerFormComponent>,
    private partnerService: PartnerService,
  ) {}

  ngOnInit(): void {
    this.partnerForm = this.formBuilder.group({
      alias: ['', [Validators.required]],
      type: ['', [Validators.required]],
      direction: ['', Validators.required],
      application: [''],
      processedFlowType: ['', Validators.required],
      description: ['', [Validators.required]],
    });

  }


  savePartner(): void {
    if (this.partnerForm.valid) {
      const partnerData: Partner = this.partnerForm.value;

        this.partnerService.addNewPartner(partnerData).subscribe({
          next: (newPartner) => {
            this.dialogRef.close(newPartner);
          },
          error: (error) => {
            console.error('Erreur lors de l\'ajout du partenaire', error);
          },
        });
      
    }
  }
}