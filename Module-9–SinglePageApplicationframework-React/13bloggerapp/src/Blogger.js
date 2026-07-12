import React, { Component } from 'react';
import BookDetails from './Components/BookDetails';
import BlogDetails from './Components/BlogDetails';
import CourseDetails from './Components/CourseDetails';

class Blogger extends Component {
    constructor(props) {
        super(props);
        this.state = {
            activeComponent: 'books'
        };
    }

    // Method 14: Conditional Rendering with Component Mapping
    renderComponent() {
        const components = {
            books: <BookDetails />,
            blogs: <BlogDetails />,
            courses: <CourseDetails />
        };

        return components[this.state.activeComponent] || <div>Component not found</div>;
    }

    render() {
        return (
            <div style={styles.container}>
                <h1 style={styles.header}>📱 Blogger Application</h1>
                
                <div style={styles.navContainer}>
                    <button 
                        onClick={() => this.setState({ activeComponent: 'books' })}
                        style={{
                            ...styles.navButton,
                            backgroundColor: this.state.activeComponent === 'books' ? '#3498db' : '#e9ecef',
                            color: this.state.activeComponent === 'books' ? 'white' : '#2c3e50'
                        }}
                    >
                        📚 Books
                    </button>
                    <button 
                        onClick={() => this.setState({ activeComponent: 'blogs' })}
                        style={{
                            ...styles.navButton,
                            backgroundColor: this.state.activeComponent === 'blogs' ? '#28a745' : '#e9ecef',
                            color: this.state.activeComponent === 'blogs' ? 'white' : '#2c3e50'
                        }}
                    >
                        📝 Blogs
                    </button>
                    <button 
                        onClick={() => this.setState({ activeComponent: 'courses' })}
                        style={{
                            ...styles.navButton,
                            backgroundColor: this.state.activeComponent === 'courses' ? '#dc3545' : '#e9ecef',
                            color: this.state.activeComponent === 'courses' ? 'white' : '#2c3e50'
                        }}
                    >
                        🎓 Courses
                    </button>
                </div>

                <div style={styles.contentContainer}>
                    {this.renderComponent()}
                </div>

                {/* Method 15: Conditional Rendering with Display Name */}
                <div style={styles.footer}>
                    <p>
                        Currently viewing: {
                            this.state.activeComponent === 'books' ? 'Book Details' :
                            this.state.activeComponent === 'blogs' ? 'Blog Details' :
                            'Course Details'
                        }
                    </p>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '1200px',
        margin: '40px auto',
        padding: '30px',
        backgroundColor: '#ffffff',
        borderRadius: '20px',
        boxShadow: '0 4px 20px rgba(0,0,0,0.1)',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '36px',
        marginBottom: '30px',
        borderBottom: '4px solid #3498db',
        paddingBottom: '15px'
    },
    navContainer: {
        display: 'flex',
        justifyContent: 'center',
        gap: '15px',
        marginBottom: '30px',
        flexWrap: 'wrap'
    },
    navButton: {
        padding: '12px 30px',
        border: 'none',
        borderRadius: '8px',
        fontSize: '16px',
        fontWeight: '600',
        cursor: 'pointer',
        transition: 'all 0.3s',
        minWidth: '120px'
    },
    contentContainer: {
        minHeight: '400px'
    },
    footer: {
        marginTop: '30px',
        paddingTop: '20px',
        borderTop: '2px solid #dee2e6',
        textAlign: 'center',
        color: '#6c757d'
    }
};

export default Blogger;