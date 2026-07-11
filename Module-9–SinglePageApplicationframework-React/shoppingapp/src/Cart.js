import React, { Component } from 'react';

class Cart extends Component {
    constructor(props) {
        super(props);
        this.itemname = props.itemname;
        this.price = props.price;
    }

    render() {
        return (
            <div style={styles.cartItem}>
                <span style={styles.itemName}>{this.itemname}</span>
                <span style={styles.itemPrice}>${this.price.toFixed(2)}</span>
            </div>
        );
    }
}

const styles = {
    cartItem: {
        display: 'flex',
        justifyContent: 'space-between',
        padding: '12px 20px',
        margin: '8px 0',
        backgroundColor: '#f8f9fa',
        borderRadius: '8px',
        borderLeft: '4px solid #007bff',
        transition: 'transform 0.2s'
    },
    itemName: {
        fontSize: '18px',
        fontWeight: '500',
        color: '#2c3e50'
    },
    itemPrice: {
        fontSize: '18px',
        fontWeight: '600',
        color: '#28a745'
    }
};

export default Cart;