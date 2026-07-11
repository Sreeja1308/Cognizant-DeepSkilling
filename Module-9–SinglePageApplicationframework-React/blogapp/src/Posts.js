import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            loading: false,
            error: null
        };
    }

    loadPosts = async () => {
        try {
            this.setState({ loading: true });
            const response = await fetch('https://jsonplaceholder.typicode.com/posts');
            const data = await response.json();
            
            const postsArray = data.map(post => 
                new Post(post.userId, post.id, post.title, post.body)
            );
            
            this.setState({ 
                posts: postsArray,
                loading: false 
            });
        } catch (error) {
            this.setState({ 
                error: error.message,
                loading: false 
            });
        }
    }

    componentDidMount() {
        this.loadPosts();
    }

    componentDidCatch(error, errorInfo) {
        alert(`Error in Posts Component: ${error.message}`);
        console.error('Error details:', errorInfo);
        this.setState({ 
            error: error.message 
        });
    }

    render() {
        const { posts, loading, error } = this.state;

        if (loading) {
            return (
                <div style={styles.loadingContainer}>
                    <h2>Loading posts...</h2>
                </div>
            );
        }

        if (error) {
            return (
                <div style={styles.errorContainer}>
                    <h2>Error loading posts</h2>
                    <p>{error}</p>
                </div>
            );
        }

        return (
            <div style={styles.container}>
                <h1 style={styles.header}>Blog Posts</h1>
                <div style={styles.postsContainer}>
                    {posts.map(post => (
                        <div key={post.id} style={styles.postCard}>
                            <h3 style={styles.postTitle}>{post.title}</h3>
                            <p style={styles.postBody}>{post.body}</p>
                            <small style={styles.postMeta}>
                                Post ID: {post.id} | User ID: {post.userId}
                            </small>
                        </div>
                    ))}
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '800px',
        margin: '0 auto',
        padding: '20px',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        borderBottom: '3px solid #3498db',
        paddingBottom: '10px',
        marginBottom: '30px'
    },
    postsContainer: {
        display: 'grid',
        gap: '20px'
    },
    postCard: {
        backgroundColor: '#f8f9fa',
        borderRadius: '8px',
        padding: '20px',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
        transition: 'transform 0.2s',
        cursor: 'pointer'
    },
    postTitle: {
        color: '#2c3e50',
        marginTop: '0',
        marginBottom: '10px',
        textTransform: 'capitalize'
    },
    postBody: {
        color: '#555',
        lineHeight: '1.6',
        marginBottom: '10px'
    },
    postMeta: {
        color: '#7f8c8d',
        fontSize: '12px',
        display: 'block',
        marginTop: '10px'
    },
    loadingContainer: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        fontSize: '20px',
        color: '#3498db'
    },
    errorContainer: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh',
        color: '#e74c3c'
    }
};

export default Posts;