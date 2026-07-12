import React, { Component } from 'react';

class GetUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            loading: true,
            error: null
        };
    }

    // ComponentDidMount lifecycle method to fetch data
    componentDidMount() {
        this.fetchUserData();
    }

    // Method to fetch user data from API
    fetchUserData = async () => {
        try {
            this.setState({ loading: true, error: null });
            
            // Fetch data from randomuser.me API
            const response = await fetch('https://api.randomuser.me/');
            
            // Check if response is ok
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            
            // Parse JSON response
            const data = await response.json();
            
            // Extract user data from response
            const userData = data.results[0];
            
            // Update state with user data
            this.setState({
                user: {
                    title: userData.name.title,
                    firstName: userData.name.first,
                    lastName: userData.name.last,
                    email: userData.email,
                    phone: userData.phone,
                    gender: userData.gender,
                    age: userData.dob.age,
                    country: userData.location.country,
                    city: userData.location.city,
                    picture: userData.picture.large,
                    thumbnail: userData.picture.thumbnail
                },
                loading: false
            });
            
            console.log('User data fetched successfully:', userData);
            
        } catch (error) {
            console.error('Error fetching user data:', error);
            this.setState({
                error: error.message,
                loading: false
            });
        }
    }

    // Method to refresh user data
    refreshUser = () => {
        this.fetchUserData();
    }

    render() {
        const { user, loading, error } = this.state;

        // Show loading state
        if (loading) {
            return (
                <div style={styles.container}>
                    <div style={styles.loadingContainer}>
                        <div style={styles.spinner}></div>
                        <p style={styles.loadingText}>Loading user data...</p>
                    </div>
                </div>
            );
        }

        // Show error state
        if (error) {
            return (
                <div style={styles.container}>
                    <div style={styles.errorContainer}>
                        <p style={styles.errorIcon}>⚠️</p>
                        <p style={styles.errorText}>Error loading user data</p>
                        <p style={styles.errorMessage}>{error}</p>
                        <button onClick={this.refreshUser} style={styles.retryButton}>
                            🔄 Retry
                        </button>
                    </div>
                </div>
            );
        }

        // Show user data
        return (
            <div style={styles.container}>
                <div style={styles.card}>
                    <h1 style={styles.header}>👤 User Profile</h1>
                    
                    <div style={styles.profileContainer}>
                        <div style={styles.imageContainer}>
                            <img 
                                src={user.picture} 
                                alt={`${user.firstName} ${user.lastName}`}
                                style={styles.profileImage}
                            />
                        </div>
                        
                        <div style={styles.userInfo}>
                            <h2 style={styles.name}>
                                {user.title} {user.firstName} {user.lastName}
                            </h2>
                            
                            <div style={styles.detailsGrid}>
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>📧 Email</span>
                                    <span style={styles.detailValue}>{user.email}</span>
                                </div>
                                
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>📱 Phone</span>
                                    <span style={styles.detailValue}>{user.phone}</span>
                                </div>
                                
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>👤 Gender</span>
                                    <span style={styles.detailValue}>
                                        {user.gender === 'male' ? '♂️ Male' : '♀️ Female'}
                                    </span>
                                </div>
                                
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>🎂 Age</span>
                                    <span style={styles.detailValue}>{user.age} years</span>
                                </div>
                                
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>📍 Country</span>
                                    <span style={styles.detailValue}>{user.country}</span>
                                </div>
                                
                                <div style={styles.detailItem}>
                                    <span style={styles.detailLabel}>🏙️ City</span>
                                    <span style={styles.detailValue}>{user.city}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div style={styles.buttonContainer}>
                        <button onClick={this.refreshUser} style={styles.refreshButton}>
                            🔄 Get New User
                        </button>
                    </div>

                    <div style={styles.apiInfo}>
                        <p style={styles.apiInfoText}>
                            Data fetched from: <strong>randomuser.me</strong>
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        minHeight: '100vh',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        padding: '20px',
        backgroundColor: '#f0f2f5'
    },
    card: {
        backgroundColor: 'white',
        padding: '40px',
        borderRadius: '20px',
        boxShadow: '0 10px 40px rgba(0,0,0,0.1)',
        maxWidth: '800px',
        width: '100%'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '32px',
        marginBottom: '30px',
        borderBottom: '3px solid #3498db',
        paddingBottom: '15px'
    },
    profileContainer: {
        display: 'flex',
        gap: '30px',
        alignItems: 'flex-start',
        marginBottom: '30px',
        flexWrap: 'wrap'
    },
    imageContainer: {
        flexShrink: '0'
    },
    profileImage: {
        width: '150px',
        height: '150px',
        borderRadius: '50%',
        objectFit: 'cover',
        border: '4px solid #3498db',
        boxShadow: '0 4px 10px rgba(0,0,0,0.1)'
    },
    userInfo: {
        flex: '1',
        minWidth: '250px'
    },
    name: {
        color: '#2c3e50',
        fontSize: '24px',
        marginBottom: '20px',
        paddingBottom: '10px',
        borderBottom: '2px solid #e9ecef'
    },
    detailsGrid: {
        display: 'grid',
        gridTemplateColumns: '1fr 1fr',
        gap: '15px'
    },
    detailItem: {
        display: 'flex',
        flexDirection: 'column',
        padding: '10px',
        backgroundColor: '#f8f9fa',
        borderRadius: '8px'
    },
    detailLabel: {
        fontSize: '12px',
        color: '#6c757d',
        fontWeight: '600',
        marginBottom: '3px'
    },
    detailValue: {
        fontSize: '16px',
        color: '#2c3e50',
        fontWeight: '500'
    },
    buttonContainer: {
        display: 'flex',
        justifyContent: 'center',
        marginTop: '20px'
    },
    refreshButton: {
        backgroundColor: '#3498db',
        color: 'white',
        border: 'none',
        padding: '14px 40px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '10px',
        cursor: 'pointer',
        transition: 'all 0.3s ease'
    },
    loadingContainer: {
        textAlign: 'center',
        padding: '60px 20px'
    },
    spinner: {
        width: '60px',
        height: '60px',
        margin: '0 auto 20px',
        border: '6px solid #e9ecef',
        borderTop: '6px solid #3498db',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
    },
    loadingText: {
        color: '#6c757d',
        fontSize: '18px'
    },
    errorContainer: {
        textAlign: 'center',
        padding: '40px 20px'
    },
    errorIcon: {
        fontSize: '48px',
        marginBottom: '15px'
    },
    errorText: {
        color: '#dc3545',
        fontSize: '20px',
        fontWeight: '600',
        marginBottom: '5px'
    },
    errorMessage: {
        color: '#6c757d',
        marginBottom: '20px'
    },
    retryButton: {
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        padding: '12px 30px',
        fontSize: '16px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s ease'
    },
    apiInfo: {
        marginTop: '20px',
        paddingTop: '15px',
        borderTop: '1px solid #e9ecef',
        textAlign: 'center'
    },
    apiInfoText: {
        color: '#6c757d',
        fontSize: '14px',
        margin: '0'
    }
};

// Add keyframes for spinner animation
const styleSheet = document.createElement("style");
styleSheet.textContent = `
    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
`;
document.head.appendChild(styleSheet);

export default GetUser;