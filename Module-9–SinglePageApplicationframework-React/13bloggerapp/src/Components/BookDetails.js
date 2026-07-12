import React, { Component } from 'react';

class BookDetails extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [
                { id: 1, title: 'The Great Gatsby', author: 'F. Scott Fitzgerald', year: 1925, price: '$15.99' },
                { id: 2, title: 'To Kill a Mockingbird', author: 'Harper Lee', year: 1960, price: '$18.99' },
                { id: 3, title: '1984', author: 'George Orwell', year: 1949, price: '$14.99' },
                { id: 4, title: 'Pride and Prejudice', author: 'Jane Austen', year: 1813, price: '$12.99' }
            ],
            showBooks: true
        };
    }

    toggleBooks = () => {
        this.setState(prevState => ({
            showBooks: !prevState.showBooks
        }));
    }

    render() {
        // Method 1: If-Else Conditional Rendering
        let bookContent;
        if (this.state.showBooks) {
            bookContent = (
                <div style={styles.bookList}>
                    {this.state.books.map(book => (
                        <div key={book.id} style={styles.bookCard}>
                            <h4 style={styles.bookTitle}>{book.title}</h4>
                            <p><strong>Author:</strong> {book.author}</p>
                            <p><strong>Year:</strong> {book.year}</p>
                            <p><strong>Price:</strong> {book.price}</p>
                        </div>
                    ))}
                </div>
            );
        } else {
            bookContent = (
                <div style={styles.emptyState}>
                    <p>📚 No books to display</p>
                </div>
            );
        }

        return (
            <div style={styles.container}>
                <h2 style={styles.header}>📚 Book Details</h2>
                
                {/* Method 1: If-Else */}
                {bookContent}

                {/* Method 2: Ternary Operator */}
                <button 
                    onClick={this.toggleBooks} 
                    style={styles.toggleButton}
                >
                    {this.state.showBooks ? 'Hide Books' : 'Show Books'}
                </button>

                {/* Method 3: Logical && Operator */}
                {this.state.showBooks && (
                    <p style={styles.count}>
                        Total Books: {this.state.books.length}
                    </p>
                )}

                {/* Method 4: Switch Statement */}
                {this.renderBookStatus()}
            </div>
        );
    }

    // Method 4: Switch Statement for Conditional Rendering
    renderBookStatus() {
        const bookCount = this.state.books.length;
        let status;
        
        switch(true) {
            case bookCount === 0:
                status = 'No books available';
                break;
            case bookCount <= 2:
                status = 'Few books available';
                break;
            case bookCount <= 5:
                status = 'Several books available';
                break;
            default:
                status = 'Many books available';
        }

        return (
            <div style={styles.statusContainer}>
                <p><strong>Status:</strong> {status}</p>
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
        borderBottom: '3px solid #3498db',
        paddingBottom: '10px'
    },
    bookList: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))',
        gap: '15px',
        marginBottom: '20px'
    },
    bookCard: {
        backgroundColor: 'white',
        padding: '15px',
        borderRadius: '8px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)',
        borderLeft: '4px solid #3498db'
    },
    bookTitle: {
        color: '#2c3e50',
        marginBottom: '10px',
        fontSize: '18px'
    },
    emptyState: {
        textAlign: 'center',
        padding: '40px',
        backgroundColor: '#e9ecef',
        borderRadius: '8px',
        marginBottom: '20px'
    },
    toggleButton: {
        backgroundColor: '#3498db',
        color: 'white',
        border: 'none',
        padding: '10px 20px',
        borderRadius: '8px',
        cursor: 'pointer',
        marginBottom: '15px',
        fontSize: '16px',
        transition: 'all 0.3s'
    },
    count: {
        color: '#6c757d',
        fontSize: '14px',
        marginTop: '10px'
    },
    statusContainer: {
        backgroundColor: '#e9ecef',
        padding: '10px 15px',
        borderRadius: '8px',
        marginTop: '10px'
    }
};

export default BookDetails;