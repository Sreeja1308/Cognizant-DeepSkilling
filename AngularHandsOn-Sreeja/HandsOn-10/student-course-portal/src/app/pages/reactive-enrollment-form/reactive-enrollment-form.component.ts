import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, AbstractControl, ReactiveFormsModule } from '@angular/forms';
import { noCourseCode, simulateEmailCheck } from '../../validators/custom.validators';

@Component({
  selector: 'app-reactive-enrollment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.component.html',
  styleUrls: ['./reactive-enrollment-form.component.css']
})
export class ReactiveEnrollmentFormComponent implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: ['', [Validators.required, Validators.email], [simulateEmailCheck]],
      courseId: [null, [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', [Validators.required]],
      agreeToTerms: [false, [Validators.requiredTrue]],
      additionalCourses: this.fb.array([])
    });
  }

  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  get additionalCoursesControls(): FormControl[] {
    return this.additionalCourses.controls as FormControl[];
  }

  get studentEmailControl(): AbstractControl {
    return this.enrollForm.get('studentEmail') as AbstractControl;
  }

  get courseIdControl(): AbstractControl {
    return this.enrollForm.get('courseId') as AbstractControl;
  }

  addCourse(): void {
    this.additionalCourses.push(this.fb.control('', [Validators.required]));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    console.log('Form Value (disabled excluded):', this.enrollForm.value);
    console.log('Form Value (all controls):', this.enrollForm.getRawValue());
    this.submitted = true;
  }

  resetForm(): void {
    this.enrollForm.reset({
      studentName: '',
      studentEmail: '',
      courseId: null,
      preferredSemester: 'Odd',
      agreeToTerms: false,
      additionalCourses: []
    });
    this.submitted = false;
  }
}