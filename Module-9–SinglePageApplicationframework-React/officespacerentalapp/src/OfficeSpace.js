import React, { Component } from 'react';

class OfficeSpace extends Component {
    constructor(props) {
        super(props);
        this.state = {
            offices: [
                {
                    id: 1,
                    name: 'Downtown Business Center',
                    rent: 45000,
                    address: '123 Main Street, City Center',
                    image: 'https://images.unsplash.com/photo-1497366216548-37526070297c?w=400&h=300&fit=crop',
                    description: 'Prime location in the heart of the city'
                },
                {
                    id: 2,
                    name: 'Tech Park Complex',
                    rent: 75000,
                    address: '456 Innovation Drive, Tech Valley',
                    image: 'https://images.unsplash.com/photo-1497366811353-6870744d04b2?w=400&h=300&fit=crop',
                    description: 'Modern workspace with all amenities'
                },
                {
                    id: 3,
                    name: 'Corporate Tower',
                    rent: 52000,
                    address: '789 Business Avenue, Financial District',
                    image: 'https://images.unsplash.com/photo-1542223616-9d9d0e5c1324?w=400&h=300&fit=crop',
                    description: 'Premium office space with city views'
                }
            ]
        };
    }

    render() {
        // Using JSX to create elements
        const heading = <h1 style={styles.mainHeading}>🏢 Office Space Rental</h1>;
        
        // Creating list of office items using JSX and map()
        const officeList = this.state.offices.map((office) => {
            // Conditional styling based on rent
            const rentColor = office.rent < 60000 ? '#dc3545' : '#28a745';
            
            return (
                <div key={office.id} style={styles.card}>
                    {/* Image attribute using JSX */}
                    <img 
                        src={office.image} 
                        alt={office.name} 
                        style={styles.image}
                    />
                    <div style={styles.details}>
                        <h3 style={styles.name}>{office.name}</h3>
                        <p style={styles.address}>📍 {office.address}</p>
                        <p style={styles.description}>{office.description}</p>
                        {/* Using JavaScript expression in JSX */}
                        <p style={{...styles.rent, color: rentColor}}>
                            💰 Rent: ₹{office.rent.toLocaleString()}/month
                        </p>
                    </div>
                </div>
            );
        });

        return (
            <div style={styles.container}>
                {heading}
                {/* Rendering JSX to DOM */}
                <div style={styles.grid}>
                    {officeList}
                </div>
            </div>
        );
    }
}

// Inline CSS in JSX
const styles = {
    container: {
        maxWidth: '1200px',
        margin: '0 auto',
        padding: '30px 20px',
        fontFamily: 'Arial, sans-serif',
        backgroundColor: '#f8f9fa',
        minHeight: '100vh'
    },
    mainHeading: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '38px',
        marginBottom: '40px',
        paddingBottom: '20px',
        borderBottom: '4px solid #3498db',
        display: 'inline-block',
        width: '100%'
    },
    grid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(350px, 1fr))',
        gap: '30px',
        marginTop: '20px'
    },
    card: {
        backgroundColor: 'white',
        borderRadius: '15px',
        overflow: 'hidden',
        boxShadow: '0 4px 15px rgba(0,0,0,0.1)',
        transition: 'transform 0.3s, box-shadow 0.3s',
        cursor: 'pointer',
        ':hover': {
            transform: 'translateY(-5px)',
            boxShadow: '0 6px 20px rgba(0,0,0,0.15)'
        }
    },
    image: {
        width: '100%',
        height: '200px',
        objectFit: 'cover',
        display: 'block'
    },
    details: {
        padding: '20px'
    },
    name: {
        color: '#2c3e50',
        fontSize: '20px',
        marginBottom: '10px',
        fontWeight: '600'
    },
    address: {
        color: '#6c757d',
        fontSize: '14px',
        marginBottom: '8px'
    },
    description: {
        color: '#495057',
        fontSize: '14px',
        marginBottom: '12px',
        lineHeight: '1.5'
    },
    rent: {
        fontSize: '18px',
        fontWeight: '700',
        marginTop: '5px'
    }
};

export default OfficeSpace;