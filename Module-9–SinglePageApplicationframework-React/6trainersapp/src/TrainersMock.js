import Trainer from './Trainer';

const trainersData = [
    new Trainer(
        1,
        'John Smith',
        'john.smith@cognizant.com',
        '+1-555-0101',
        'React',
        ['JavaScript', 'React', 'Redux', 'Node.js']
    ),
    new Trainer(
        2,
        'Sarah Johnson',
        'sarah.johnson@cognizant.com',
        '+1-555-0102',
        'Angular',
        ['TypeScript', 'Angular', 'RxJS', 'HTML/CSS']
    ),
    new Trainer(
        3,
        'Michael Brown',
        'michael.brown@cognizant.com',
        '+1-555-0103',
        'Java',
        ['Java', 'Spring Boot', 'Hibernate', 'Microservices']
    ),
    new Trainer(
        4,
        'Emily Davis',
        'emily.davis@cognizant.com',
        '+1-555-0104',
        'Python',
        ['Python', 'Django', 'Flask', 'Machine Learning']
    ),
    new Trainer(
        5,
        'David Wilson',
        'david.wilson@cognizant.com',
        '+1-555-0105',
        'Cloud',
        ['AWS', 'Azure', 'DevOps', 'Kubernetes']
    )
];

export default trainersData;