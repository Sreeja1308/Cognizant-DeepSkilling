import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { EnrollmentService } from '../../services/enrollment.service';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './course-detail.component.html',
  styleUrl: './course-detail.component.css'
})
export class CourseDetailComponent implements OnInit {
  course: Course | undefined;
  courseId: string | null = null;
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private courseService: CourseService,
    private enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {
    // Read the :id parameter from the route
    this.courseId = this.route.snapshot.paramMap.get('id');
    
    if (this.courseId) {
      const id = parseInt(this.courseId, 10);
      this.course = this.courseService.getCourseById(id);
      this.isLoading = false;
      
      if (!this.course) {
        // Course not found, navigate to 404
        this.router.navigate(['/not-found']);
      }
    }
  }

  isEnrolled(): boolean {
    return this.course ? this.enrollmentService.isEnrolled(this.course.id) : false;
  }

  toggleEnrollment(): void {
    if (this.course) {
      this.enrollmentService.toggleEnrollment(this.course.id);
    }
  }

  goBack(): void {
    this.router.navigate(['/courses']);
  }
}