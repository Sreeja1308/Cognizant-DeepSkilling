import React, { Component } from 'react';

class CourseDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            courses: [
                { id: 1, name: 'React Development', duration: '6 weeks', level: 'Intermediate', enrolled: 45 },
                { id: 2, name: 'JavaScript Fundamentals', duration: '4 weeks', level: 'Beginner', enrolled: 78 },
                { id: 3, name: 'Advanced Node.js', duration: '8 weeks', level: 'Advanced', enrolled: 23 }
            ],
            showEnrolled: true,
            filterLevel: 'all'
        };
    }

    // Method 9: Conditional Rendering with Map and Filter
    renderFilteredCourses() {
        let filteredCourses = this.state.courses;
        
        if (this.state.filterLevel !== 'all') {
            filteredCourses = this.state.courses.filter(
                course => course.level === this.state.filterLevel
            );
        }

        if (filteredCourses.length === 0) {
            return <div style={styles.noCourses}>No courses found for this level</div>;
        }

        return filteredCourses.map(course => (
            <div key={course.id} style={styles.courseCard}>
                <h4 style={styles.courseName}>{course.name}</h4>
                <p><strong>Duration:</strong> {course.duration}</p>
                <p><strong>Level:</strong> 
                    <span style={{
                        ...styles.levelBadge,
                        backgroundColor: course.level === 'Beginner' ? '#28a745' :
                                       course.level === 'Intermediate' ? '#ffc107' : '#dc3545'
                    }}>
                        {course.level}
                    </span>
                </p>
                {/* Method 10: Conditional Rendering with && */}
                {this.state.showEnrolled && (
                    <p><strong>Enrolled:</strong> {course.enrolled} students</p>
                )}
                <button 
                    onClick={() => this.handleEnroll(course.id)}
                    style={styles.enrollButton}
                >
                    Enroll Now
                </button>
            </div>
        ));
    }

    handleEnroll = (courseId) => {
        this.setState(prevState => ({
            courses: prevState.courses.map(course =>
                course.id === courseId 
                    ? { ...course, enrolled: course.enrolled + 1 }
                    : course
            )
        }));
        alert('✅ Successfully enrolled in the course!');
    }

    // Method 11: Conditional Rendering with Multiple Conditions
    renderCourseStats() {
        const totalCourses = this.state.courses.length;
        const totalEnrolled = this.state.courses.reduce((sum, c) => sum + c.enrolled, 0);
        const avgEnrollment = totalCourses > 0 ? (totalEnrolled / totalCourses).toFixed(0) : 0;

        return (
            <div style={styles.statsGrid}>
                <div style={styles.statCard}>
                    <h4>Total Courses</h4>
                    <p>{totalCourses}</p>
                </div>
                <div style={styles.statCard}>
                    <h4>Total Enrollments</h4>
                    <p>{totalEnrolled}</p>
                </div>
                <div style={styles.statCard}>
                    <h4>Average Enrollment</h4>
                    <p>{avgEnrollment}</p>
                </div>
            </div>
        );
    }

    // Method 12: Conditional Rendering with Switch Case
    renderLevelFilter() {
        return (
            <div style={styles.filterContainer}>
                <label>Filter by Level: </label>
                <select 
                    value={this.state.filterLevel}
                    onChange={(e) => this.setState({ filterLevel: e.target.value })}
                    style={styles.filterSelect}
                >
                    <option value="all">All Levels</option>
                    <option value="Beginner">Beginner</option>
                    <option value="Intermediate">Intermediate</option>
                    <option value="Advanced">Advanced</option>
                </select>
            </div>
        );
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.header}>🎓 Course Details</h2>
                
                {/* Method 11: Conditional Rendering with Multiple Conditions */}
                {this.renderCourseStats()}

                {/* Method 12: Conditional Rendering with Switch Case */}
                {this.renderLevelFilter()}

                <div style={styles.toggleContainer}>
                    <label>
                        <input 
                            type="checkbox"
                            checked={this.state.showEnrolled}
                            onChange={() => this.setState(prev => ({ 
                                showEnrolled: !prev.showEnrolled 
                            }))}
                        />
                        Show Enrollment Numbers
                    </label>
                </div>

                {/* Method 9: Conditional Rendering with Map and Filter */}
                <div style={styles.courseGrid}>
                    {this.renderFilteredCourses()}
                </div>

                {/* Method 13: Conditional Rendering with Null Return */}
                {this.state.courses.length === 0 && this.renderEmptyState()}
            </div>
        );
    }

    // Method 13: Conditional Rendering with Null Return
    renderEmptyState() {
        return null; // Nothing to render when no courses
    }
}

const styles = {
    container: {
        backgroundColor: '#f8f9fa',
        padding: '25px',
        borderRadius: '15px',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)'
    },
    header: {
        color: '#2c3e50',
        fontSize: '24px',
        marginBottom: '20px',
        borderBottom: '3px solid #dc3545',
        paddingBottom: '10px'
    },
    statsGrid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))',
        gap: '15px',
        marginBottom: '20px'
    },
    statCard: {
        backgroundColor: 'white',
        padding: '15px',
        borderRadius: '8px',
        textAlign: 'center',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
    },
    filterContainer: {
        marginBottom: '20px',
        padding: '15px',
        backgroundColor: 'white',
        borderRadius: '8px'
    },
    filterSelect: {
        padding: '8px 12px',
        marginLeft: '10px',
        borderRadius: '5px',
        border: '1px solid #ced4da'
    },
    toggleContainer: {
        marginBottom: '20px',
        padding: '10px',
        backgroundColor: 'white',
        borderRadius: '8px'
    },
    courseGrid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
        gap: '15px'
    },
    courseCard: {
        backgroundColor: 'white',
        padding: '15px',
        borderRadius: '8px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        borderLeft: '4px solid #dc3545'
    },
    courseName: {
        color: '#2c3e50',
        marginBottom: '10px',
        fontSize: '18px'
    },
    levelBadge: {
        display: 'inline-block',
        padding: '3px 10px',
        borderRadius: '20px',
        color: 'white',
        fontSize: '12px',
        marginLeft: '5px'
    },
    enrollButton: {
        backgroundColor: '#dc3545',
        color: 'white',
        border: 'none',
        padding: '8px 20px',
        borderRadius: '5px',
        cursor: 'pointer',
        marginTop: '10px',
        transition: 'all 0.3s'
    },
    noCourses: {
        textAlign: 'center',
        padding: '30px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        color: '#6c757d',
        gridColumn: '1 / -1'
    }
};

export default CourseDetails;