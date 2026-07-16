import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css'
})
export class EnrollmentFormComponent {
  // Form model properties
  studentName: string = '';
  studentEmail: string = '';
  courseId: number | null = null;
  preferredSemester: string = '';
  agreeToTerms: boolean = false;
  submitted = false;

  onSubmit(form: NgForm): void {
    if (form.valid) {
      console.log('Form Value:', form.value);
      console.log('Form Valid:', form.valid);
      this.submitted = true;
      // Reset submitted after 3 seconds
      setTimeout(() => {
        this.submitted = false;
      }, 3000);
    }
  }

  onReset(form: NgForm): void {
    form.resetForm();
    this.submitted = false;
    // Reset form model properties
    this.studentName = '';
    this.studentEmail = '';
    this.courseId = null;
    this.preferredSemester = '';
    this.agreeToTerms = false;
  }
}