import React, { Component } from 'react';

class GuestUser extends Component {
    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.header}>👤 Guest User</h2>
                <div style={styles.content}>
                    <p style={styles.message}>
                        Welcome to the Flight Booking System!
                    </p>
                    <p style={styles.info}>
                        Please login to book tickets and view flight details.
                    </p>
                    
                    <div style={styles.features}>
                        <div style={styles.featureCard}>
                            <h3>✈️ Browse Flights</h3>
                            <p>View available flights and schedules</p>
                        </div>
                        <div style={styles.featureCard}>
                            <h3>🔍 Check Availability</h3>
                            <p>Search for flights to your destination</p>
                        </div>
                        <div style={styles.featureCard}>
                            <h3>💰 View Prices</h3>
                            <p>Check ticket prices and deals</p>
                        </div>
                    </div>
                    
                    <div style={styles.loginPrompt}>
                        <p style={styles.promptText}>
                            🔐 Login to book your tickets now!
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        backgroundColor: '#f8f9fa',
        padding: '30px',
        borderRadius: '15px',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
        minHeight: '400px'
    },
    header: {
        color: '#6c757d',
        fontSize: '28px',
        marginBottom: '20px',
        borderBottom: '2px solid #dee2e6',
        paddingBottom: '10px'
    },
    content: {
        textAlign: 'center'
    },
    message: {
        fontSize: '24px',
        color: '#2c3e50',
        marginBottom: '10px'
    },
    info: {
        fontSize: '16px',
        color: '#6c757d',
        marginBottom: '30px'
    },
    features: {
        display: 'flex',
        justifyContent: 'center',
        gap: '20px',
        marginBottom: '30px',
        flexWrap: 'wrap'
    },
    featureCard: {
        backgroundColor: 'white',
        padding: '20px',
        borderRadius: '10px',
        width: '200px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        transition: 'transform 0.3s'
    },
    loginPrompt: {
        backgroundColor: '#fff3cd',
        padding: '15px',
        borderRadius: '10px',
        marginTop: '20px'
    },
    promptText: {
        fontSize: '18px',
        color: '#856404',
        fontWeight: '600',
        margin: '0'
    }
};

export default GuestUser;