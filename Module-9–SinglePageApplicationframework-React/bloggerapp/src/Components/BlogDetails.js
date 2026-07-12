import React, { Component } from 'react';

class BlogDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            blogs: [
                { id: 1, title: 'Getting Started with React', author: 'John Doe', likes: 120, comments: 45 },
                { id: 2, title: 'Understanding JavaScript Closures', author: 'Jane Smith', likes: 85, comments: 30 },
                { id: 3, title: 'CSS Grid vs Flexbox', author: 'Mike Johnson', likes: 200, comments: 78 }
            ],
            selectedCategory: 'all'
        };
    }

    // Method 5: Element Variables
    renderBlogList() {
        if (this.state.blogs.length === 0) {
            return <div style={styles.noBlogs}>No blogs available</div>;
        }

        return this.state.blogs.map(blog => (
            <div key={blog.id} style={styles.blogCard}>
                <h4 style={styles.blogTitle}>{blog.title}</h4>
                <p><strong>Author:</strong> {blog.author}</p>
                <div style={styles.blogStats}>
                    <span>❤️ {blog.likes} likes</span>
                    <span>💬 {blog.comments} comments</span>
                </div>
            </div>
        ));
    }

    // Method 6: Inline If with Logical && Operator
    renderPopularBlogs() {
        const popularBlogs = this.state.blogs.filter(blog => blog.likes > 100);
        return (
            <div style={styles.popularSection}>
                {popularBlogs.length > 0 && (
                    <>
                        <h4>🔥 Popular Blogs</h4>
                        <div style={styles.popularList}>
                            {popularBlogs.map(blog => (
                                <span key={blog.id} style={styles.popularTag}>
                                    {blog.title}
                                </span>
                            ))}
                        </div>
                    </>
                )}
            </div>
        );
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.header}>📝 Blog Details</h2>
                
                {/* Method 5: Element Variables */}
                <div style={styles.blogGrid}>
                    {this.renderBlogList()}
                </div>

                {/* Method 6: Inline If with Logical && Operator */}
                {this.renderPopularBlogs()}

                {/* Method 7: Ternary Operator with Conditional Classes */}
                <div style={styles.statusBar}>
                    <p style={{
                        color: this.state.blogs.length > 0 ? '#28a745' : '#dc3545',
                        fontWeight: '600'
                    }}>
                        Status: {this.state.blogs.length > 0 ? 'Active' : 'Inactive'}
                    </p>
                    <p>Total Blogs: {this.state.blogs.length}</p>
                </div>

                {/* Method 8: Conditional Rendering with Multiple Conditions */}
                {this.state.blogs.length > 0 && this.state.blogs.length <= 3 && (
                    <div style={styles.infoBox}>
                        <p>💡 Tip: You have {this.state.blogs.length} blog(s). Consider adding more content!</p>
                    </div>
                )}
            </div>
        );
    }
}

const styles = {
    container: {
        backgroundColor: '#f8f9fa',
        padding: '25px',
        borderRadius: '15px',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
        marginBottom: '30px'
    },
    header: {
        color: '#2c3e50',
        fontSize: '24px',
        marginBottom: '20px',
        borderBottom: '3px solid #28a745',
        paddingBottom: '10px'
    },
    blogGrid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
        gap: '15px',
        marginBottom: '20px'
    },
    blogCard: {
        backgroundColor: 'white',
        padding: '15px',
        borderRadius: '8px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        borderLeft: '4px solid #28a745'
    },
    blogTitle: {
        color: '#2c3e50',
        marginBottom: '10px',
        fontSize: '18px'
    },
    blogStats: {
        display: 'flex',
        gap: '15px',
        marginTop: '10px',
        fontSize: '14px',
        color: '#6c757d'
    },
    noBlogs: {
        textAlign: 'center',
        padding: '30px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        color: '#6c757d'
    },
    popularSection: {
        backgroundColor: '#fff3cd',
        padding: '15px',
        borderRadius: '8px',
        marginBottom: '20px'
    },
    popularList: {
        display: 'flex',
        flexWrap: 'wrap',
        gap: '10px',
        marginTop: '10px'
    },
    popularTag: {
        backgroundColor: '#ffc107',
        padding: '5px 15px',
        borderRadius: '20px',
        fontSize: '14px',
        color: '#856404'
    },
    statusBar: {
        display: 'flex',
        justifyContent: 'space-between',
        padding: '15px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        marginBottom: '15px'
    },
    infoBox: {
        backgroundColor: '#d1ecf1',
        padding: '15px',
        borderRadius: '8px',
        color: '#0c5460',
        marginTop: '10px'
    }
};

export default BlogDetails;