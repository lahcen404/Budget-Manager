import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-transaction-form',
  templateUrl: './transaction-form.component.html',
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./transaction-form.component.css']
})
export class TransactionFormComponent implements OnInit {
  @Input() transaction: any = null;
  @Output() formSubmit = new EventEmitter<any>();
  @Output() cancel = new EventEmitter<void>();

  transactionForm!: FormGroup;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.transactionForm = this.fb.group({
      id: [this.transaction?.id || null],
      amount: [this.transaction?.amount || '', [Validators.required]],
      date: [this.transaction?.date || '', [Validators.required]],
      description: [this.transaction?.description || '', [Validators.required]],
      category: this.fb.group({
        id: [this.transaction?.category?.id || null],
        name: [this.transaction?.category?.name || '']
      })
    });
  }

  submit(): void {
    if (this.transactionForm.valid) {
      this.formSubmit.emit(this.transactionForm.value);
    }
  }

  cancelForm(): void {
    this.cancel.emit();
  }
}
