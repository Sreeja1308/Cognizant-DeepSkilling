import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';

// Custom synchronous validator
export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  if (control.value && control.value.toString().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// Custom async validator
export function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      if (control.value && control.value.includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrl: './reactive-enrollment-form.component.css'
})
export class ReactiveEnrollmentFormComponent implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;
  formValue: any = null;
  rawFormValue: any = null;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: ['', [Validators.required, Validators.email], [simulateEmailCheck]],
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  // Typed getter for additionalCourses - better than casting in template
  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourse(): void {
    this.additionalCourses.push(this.fb.control('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    if (this.enrollForm.valid) {
      // enrollForm.value excludes disabled controls, enrollForm.getRawValue() includes all controls
      this.formValue = this.enrollForm.value;
      this.rawFormValue = this.enrollForm.getRawValue();
      this.submitted = true;
      console.log('Form Value (excludes disabled):', this.formValue);
      console.log('Raw Form Value (includes all):', this.rawFormValue);
      
      setTimeout(() => {
        this.submitted = false;
      }, 5000);
    }
  }

  onReset(): void {
    this.enrollForm.reset({
      studentName: '',
      studentEmail: '',
      courseId: null,
      preferredSemester: 'Odd',
      agreeToTerms: false
    });
    this.additionalCourses.clear();
    this.submitted = false;
    this.formValue = null;
    this.rawFormValue = null;
  }
}