import React, { Component } from 'react';

class LoggedInUser extends Component {
    constructor(props) {
        super(props);
        this.state = {
            flights: [
                { id: 1, from: 'New York', to: 'London', date: '2026-07-15', price: '$550', seats: 12 },
                { id: 2, from: 'London', to: 'Paris', date: '2026-07-16', price: '$200', seats: 8 },
                { id: 3, from: 'Tokyo', to: 'Singapore', date: '2026-07-17', price: '$450', seats: 15 },
                { id: 4, from: 'Dubai', to: 'Mumbai', date: '2026-07-18', price: '$300', seats: 20 },
                { id: 5, from: 'Sydney', to: 'Auckland', date: '2026-07-19', price: '$380', seats: 10 }
            ],
            bookedTickets: []
        };
    }

    bookTicket = (flightId) => {
        const flight = this.state.flights.find(f => f.id === flightId);
        if (flight && flight.seats > 0) {
            this.setState((prevState) => ({
                flights: prevState.flights.map(f => 
                    f.id === flightId ? { ...f, seats: f.seats - 1 } : f
                ),
                bookedTickets: [...prevState.bookedTickets, flight]
            }));
            alert(`✅ Ticket booked for ${flight.from} to ${flight.to}!`);
        } else {
            alert('❌ No seats available for this flight!');
        }
    }

    render() {
        return (
            <div style={styles.container}>
                <h2 style={styles.header}>👤 Logged In User</h2>
                
                <div style={styles.welcomeSection}>
                    <p style={styles.welcomeMessage}>
                        Welcome back! You can book tickets for available flights.
                    </p>
                </div>

                <div style={styles.flightSection}>
                    <h3 style={styles.sectionTitle}>✈️ Available Flights</h3>
                    <div style={styles.flightGrid}>
                        {this.state.flights.map(flight => (
                            <div key={flight.id} style={styles.flightCard}>
                                <div style={styles.flightRoute}>
                                    <span style={styles.city}>{flight.from}</span>
                                    <span style={styles.arrow}>→</span>
                                    <span style={styles.city}>{flight.to}</span>
                                </div>
                                <div style={styles.flightDetails}>
                                    <p><strong>Date:</strong> {flight.date}</p>
                                    <p><strong>Price:</strong> {flight.price}</p>
                                    <p><strong>Seats Available:</strong> {flight.seats}</p>
                                </div>
                                <button 
                                    onClick={() => this.bookTicket(flight.id)}
                                    style={flight.seats > 0 ? styles.bookButton : styles.bookedButton}
                                    disabled={flight.seats === 0}
                                >
                                    {flight.seats > 0 ? '🎫 Book Ticket' : '❌ Sold Out'}
                                </button>
                            </div>
                        ))}
                    </div>
                </div>

                {this.state.bookedTickets.length > 0 && (
                    <div style={styles.bookedSection}>
                        <h3 style={styles.sectionTitle}>🎟️ Your Booked Tickets ({this.state.bookedTickets.length})</h3>
                        <div style={styles.bookedList}>
                            {this.state.bookedTickets.map((ticket, index) => (
                                <div key={index} style={styles.bookedCard}>
                                    <span>{ticket.from} → {ticket.to}</span>
                                    <span>{ticket.date}</span>
                                    <span>{ticket.price}</span>
                                </div>
                            ))}
                        </div>
                    </div>
                )}
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
        color: '#28a745',
        fontSize: '28px',
        marginBottom: '20px',
        borderBottom: '2px solid #28a745',
        paddingBottom: '10px'
    },
    welcomeSection: {
        backgroundColor: '#d4edda',
        padding: '15px',
        borderRadius: '10px',
        marginBottom: '25px'
    },
    welcomeMessage: {
        fontSize: '18px',
        color: '#155724',
        margin: '0',
        textAlign: 'center'
    },
    flightSection: {
        marginBottom: '30px'
    },
    sectionTitle: {
        color: '#2c3e50',
        fontSize: '20px',
        marginBottom: '15px'
    },
    flightGrid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(250px, 1fr))',
        gap: '20px'
    },
    flightCard: {
        backgroundColor: 'white',
        padding: '20px',
        borderRadius: '10px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        transition: 'transform 0.3s'
    },
    flightRoute: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        gap: '10px',
        marginBottom: '15px',
        fontSize: '20px',
        fontWeight: '600',
        color: '#2c3e50'
    },
    city: {
        fontSize: '18px'
    },
    arrow: {
        color: '#007bff',
        fontWeight: 'bold'
    },
    flightDetails: {
        marginBottom: '15px'
    },
    bookButton: {
        backgroundColor: '#007bff',
        color: 'white',
        border: 'none',
        padding: '10px 20px',
        borderRadius: '8px',
        cursor: 'pointer',
        width: '100%',
        fontSize: '16px',
        fontWeight: '600',
        transition: 'all 0.3s'
    },
    bookedButton: {
        backgroundColor: '#6c757d',
        color: 'white',
        border: 'none',
        padding: '10px 20px',
        borderRadius: '8px',
        cursor: 'not-allowed',
        width: '100%',
        fontSize: '16px',
        fontWeight: '600'
    },
    bookedSection: {
        marginTop: '20px',
        paddingTop: '20px',
        borderTop: '2px solid #dee2e6'
    },
    bookedList: {
        display: 'flex',
        flexDirection: 'column',
        gap: '10px'
    },
    bookedCard: {
        backgroundColor: '#e9ecef',
        padding: '15px',
        borderRadius: '8px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        paddingLeft: '20px',
        paddingRight: '20px'
    }
};

export default LoggedInUser;