import React, { Component } from 'react';
import Cart from './Cart';

class OnlineShopping extends Component {
    constructor(props) {
        super(props);
        this.state = {
            cartItems: [
                { itemname: 'Laptop', price: 999.99 },
                { itemname: 'Smartphone', price: 699.50 },
                { itemname: 'Headphones', price: 149.99 },
                { itemname: 'Smart Watch', price: 249.00 },
                { itemname: 'Tablet', price: 399.75 }
            ]
        };
    }

    calculateTotal = () => {
        return this.state.cartItems.reduce((total, item) => total + item.price, 0);
    }

    render() {
        return (
            <div style={styles.container}>
                <h1 style={styles.header}>🛒 Online Shopping Cart</h1>
                
                <div style={styles.cartContainer}>
                    <h2 style={styles.subHeader}>Your Items</h2>
                    
                    <div style={styles.cartList}>
                        {this.state.cartItems.map((item, index) => (
                            <Cart 
                                key={index}
                                itemname={item.itemname}
                                price={item.price}
                            />
                        ))}
                    </div>
                    
                    <div style={styles.totalContainer}>
                        <span style={styles.totalLabel}>Total Amount:</span>
                        <span style={styles.totalPrice}>
                            ${this.calculateTotal().toFixed(2)}
                        </span>
                    </div>
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '700px',
        margin: '40px auto',
        padding: '30px',
        backgroundColor: '#ffffff',
        borderRadius: '15px',
        boxShadow: '0 4px 20px rgba(0,0,0,0.1)',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '32px',
        marginBottom: '30px',
        borderBottom: '3px solid #007bff',
        paddingBottom: '15px'
    },
    subHeader: {
        color: '#495057',
        fontSize: '22px',
        marginBottom: '20px',
        paddingLeft: '5px'
    },
    cartContainer: {
        backgroundColor: '#f8f9fa',
        borderRadius: '10px',
        padding: '20px'
    },
    cartList: {
        marginBottom: '20px'
    },
    totalContainer: {
        display: 'flex',
        justifyContent: 'space-between',
        padding: '15px 20px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        borderTop: '2px solid #007bff',
        marginTop: '10px'
    },
    totalLabel: {
        fontSize: '20px',
        fontWeight: '600',
        color: '#2c3e50'
    },
    totalPrice: {
        fontSize: '22px',
        fontWeight: '700',
        color: '#28a745'
    }
};

export default OnlineShopping;