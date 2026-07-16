import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';
import { LoadingService } from '../../services/loading.service';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, CourseCardComponent, RouterModule, FormsModule],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent implements OnInit, OnDestroy {
  courses: Course[] = [];
  selectedCourseId: number | null = null;
  isLoading = true;
  errorMessage: string | null = null;
  searchTerm: string = '';
  private subscriptions: Subscription[] = [];

  constructor(
    private courseService: CourseService,
    private enrollmentService: EnrollmentService,
    private router: Router,
    private route: ActivatedRoute,
    private loadingService: LoadingService
  ) {}

  ngOnInit(): void {
    // Read query parameter from URL
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params['search'] || '';
    });

    // Load enrolled course IDs first
    this.enrollmentService.loadEnrolledCourseIds().subscribe();

    // Load courses with loading state
    this.loadCourses();
  }

  loadCourses(): void {
    const sub = this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courses = courses;
        this.isLoading = false;
        this.errorMessage = null;
      },
      error: (err) => {
        this.errorMessage = err.message;
        this.isLoading = false;
        console.error('Error loading courses:', err);
      }
    });
    this.subscriptions.push(sub);
  }

  onEnroll(courseId: number): void {
    const sub = this.enrollmentService.enroll(courseId).subscribe({
      next: () => {
        this.selectedCourseId = courseId;
        console.log('Enrollment successful for course:', courseId);
      },
      error: (err) => {
        console.error('Enrollment failed:', err);
        alert('Failed to enroll. Please try again.');
      }
    });
    this.subscriptions.push(sub);
  }

  onUnenroll(courseId: number): void {
    const sub = this.enrollmentService.unenroll(courseId).subscribe({
      next: () => {
        this.selectedCourseId = null;
        console.log('Unenrollment successful for course:', courseId);
      },
      error: (err) => {
        console.error('Unenrollment failed:', err);
        alert('Failed to unenroll. Please try again.');
      }
    });
    this.subscriptions.push(sub);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrollmentService.isEnrolled(courseId);
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onCourseClick(courseId: number): void {
    this.router.navigate(['/courses', courseId]);
  }

  onSearch(): void {
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null }
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}