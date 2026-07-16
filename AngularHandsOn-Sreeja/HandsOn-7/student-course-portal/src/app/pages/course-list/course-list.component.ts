import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { CourseCardComponent } from '../../components/course-card/course-card.component';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-course-list',
  standalone: true,
  imports: [CommonModule, CourseCardComponent, RouterModule, FormsModule],
  templateUrl: './course-list.component.html',
  styleUrl: './course-list.component.css'
})
export class CourseListComponent implements OnInit {
  courses: Course[] = [];
  selectedCourseId: number | null = null;
  isLoading = true;
  searchTerm: string = '';

  constructor(
    private courseService: CourseService,
    private enrollmentService: EnrollmentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Read query parameter from URL
    this.route.snapshot.queryParamMap.get('search');
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params['search'] || '';
    });

    this.courses = this.courseService.getCourses();
    
    setTimeout(() => {
      this.isLoading = false;
    }, 1500);
  }

  onEnroll(courseId: number): void {
    this.enrollmentService.toggleEnrollment(courseId);
    this.selectedCourseId = courseId;
    console.log('Toggled enrollment for course:', courseId);
  }

  isEnrolled(courseId: number): boolean {
    return this.enrollmentService.isEnrolled(courseId);
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onCourseClick(courseId: number): void {
    // Navigate to course detail page
    this.router.navigate(['/courses', courseId]);
  }

  onSearch(): void {
    // Update URL with query parameter
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null }
    });
  }
}