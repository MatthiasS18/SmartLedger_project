import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Observable } from 'rxjs';

export interface DialogData {
  title: string;
  fields: { 
    label: string; 
    key: string; 
    value: string;
    type?: 'text' | 'date' | 'select' | 'number';
    options?: string[]; 
  }[];
  onConfirm: (data: any) => Observable<any>;
}

@Component({
  selector: 'app-reusable-dialog',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatDialogTitle, MatDialogContent, MatDialogActions, MatSelectModule, MatDatepickerModule],
  templateUrl: './Dialog.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReusableDialogComponent {
  readonly dialogRef = inject(MatDialogRef<ReusableDialogComponent>);
  readonly data = inject<DialogData>(MAT_DIALOG_DATA);

  onCancel(): void {
    this.dialogRef.close(null);
  }

  onConfirm(): void {
    const payload = Object.fromEntries(
      this.data.fields.map(f => {
        if (f.type === 'date' && f.value) {
          const date = new Date(f.value);
          const formatted = date.toISOString().split('T')[0];
          return [f.key, formatted];
        }
        const asNumber = Number(f.value);
        if (f.value !== '' && !isNaN(asNumber)) {
          return [f.key, asNumber];
        }
        return [f.key, f.value];
      })
    );

    this.data.onConfirm(payload).subscribe({
      next: (result) => this.dialogRef.close(result),
      error: (err) => console.error('Error:', err)
    });
  }
}