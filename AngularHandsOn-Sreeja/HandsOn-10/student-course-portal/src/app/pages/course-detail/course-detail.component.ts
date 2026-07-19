import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CourseService } from '../../services/course.service';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-course-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  template: `
    <div *ngIf="course" class="course-detail">
      <h2>{{ course.name }}</h2>
      <p><strong>Code:</strong> {{ course.code }}</p>
      <p><strong>Credits:</strong> {{ course.credits }}</p>
      <p><strong>Status:</strong> {{ course.gradeStatus }}</p>
      <p><strong>Description:</strong> {{ course.description || 'No description available.' }}</p>
      <p><strong>Instructor:</strong> {{ course.instructor || 'TBD' }}</p>
      <p><strong>Schedule:</strong> {{ course.schedule || 'TBD' }}</p>
      <a routerLink="/courses">Back to Courses</a>
    </div>
    <div *ngIf="!course" class="loading">
      <p>Loading course details...</p>
    </div>
  `,
  styles: [`
    .course-detail {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    .course-detail h2 {
      color: #2c3e50;
      margin-bottom: 20px;
    }
    .course-detail p {
      margin: 10px 0;
      line-height: 1.6;
    }
    .course-detail a {
      display: inline-block;
      margin-top: 20px;
      color: #3498db;
      text-decoration: none;
    }
    .course-detail a:hover {
      text-decoration: underline;
    }
    .loading {
      text-align: center;
      padding: 40px;
      color: #7f8c8d;
    }
  `]
})
export class CourseDetailComponent implements OnInit {
  course: Course | undefined;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.courseService.getCourseById(id).subscribe({
        next: (course) => {
          this.course = course;
        },
        error: () => {
          console.error('Failed to load course');
        }
      });
    }
  }
}