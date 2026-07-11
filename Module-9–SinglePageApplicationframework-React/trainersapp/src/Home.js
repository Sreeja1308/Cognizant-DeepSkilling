import React from 'react';

function Home() {
    return (
        <div style={styles.container}>
            <h1 style={styles.header}>Welcome to Trainers Management</h1>
            <p style={styles.description}>
                This application helps you manage and view trainer information 
                for Cognizant Academy.
            </p>
            <div style={styles.features}>
                <div style={styles.featureCard}>
                    <h3>📋 Trainer List</h3>
                    <p>View all trainers and their expertise</p>
                </div>
                <div style={styles.featureCard}>
                    <h3>🔍 Trainer Details</h3>
                    <p>Get detailed information about each trainer</p>
                </div>
                <div style={styles.featureCard}>
                    <h3>📊 Technology Stack</h3>
                    <p>Explore trainers by technology and skills</p>
                </div>
            </div>
        </div>
    );
}

const styles = {
    container: {
        padding: '40px 20px',
        maxWidth: '1200px',
        margin: '0 auto',
        textAlign: 'center'
    },
    header: {
        color: '#2c3e50',
        fontSize: '36px',
        marginBottom: '20px'
    },
    description: {
        fontSize: '18px',
        color: '#555',
        maxWidth: '600px',
        margin: '0 auto 40px auto',
        lineHeight: '1.6'
    },
    features: {
        display: 'flex',
        justifyContent: 'center',
        gap: '30px',
        flexWrap: 'wrap'
    },
    featureCard: {
        backgroundColor: '#f8f9fa',
        padding: '30px',
        borderRadius: '10px',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)',
        width: '200px',
        transition: 'transform 0.3s'
    }
};

export default Home;