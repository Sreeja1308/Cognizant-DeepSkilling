import React, { Component } from 'react';
import GuestUser from './Components/GuestUser';
import LoggedInUser from './Components/LoggedInUser';

class TicketBooking extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoggedIn: false
        };
        this.handleLogin = this.handleLogin.bind(this);
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogin() {
        this.setState({
            isLoggedIn: true
        });
    }

    handleLogout() {
        this.setState({
            isLoggedIn: false
        });
    }

    render() {
        let userComponent;

        if (this.state.isLoggedIn) {
            userComponent = <LoggedInUser />;
        } else {
            userComponent = <GuestUser />;
        }

        return (
            <div style={styles.container}>
                <h1 style={styles.header}>✈️ Flight Ticket Booking</h1>
                
                <div style={styles.buttonContainer}>
                    <button 
                        onClick={this.state.isLoggedIn ? this.handleLogout : this.handleLogin}
                        style={this.state.isLoggedIn ? styles.logoutButton : styles.loginButton}
                    >
                        {this.state.isLoggedIn ? '🚪 Logout' : '🔑 Login'}
                    </button>
                    <span style={styles.status}>
                        Status: {this.state.isLoggedIn ? 'Logged In' : 'Guest'}
                    </span>
                </div>

                <div style={styles.content}>
                    {userComponent}
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
        marginBottom: '20px',
        borderBottom: '4px solid #3498db',
        paddingBottom: '15px'
    },
    buttonContainer: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        gap: '20px',
        marginBottom: '25px',
        padding: '15px',
        backgroundColor: '#f8f9fa',
        borderRadius: '10px'
    },
    loginButton: {
        backgroundColor: '#28a745',
        color: 'white',
        border: 'none',
        padding: '12px 40px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    logoutButton: {
        backgroundColor: '#dc3545',
        color: 'white',
        border: 'none',
        padding: '12px 40px',
        fontSize: '18px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    status: {
        fontSize: '16px',
        color: '#495057',
        fontWeight: '500',
        padding: '8px 16px',
        backgroundColor: 'white',
        borderRadius: '20px',
        boxShadow: '0 1px 3px rgba(0,0,0,0.1)'
    },
    content: {
        marginTop: '20px'
    }
};

export default TicketBooking;