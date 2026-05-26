
# Task Management

Our project is a collaborative group management and task tracking system with integrated billing and payment functionality. It enables users to organize into groups, manage shared tasks, track progress, and handle financial transactions related to group activities.



## Features

### Authentication
- Register
- Login
- JWT authentication
- Password encryption (BCrypt)
### Groups
- Create groups
- View user groups
- Group members
- Role management
### Tasks
- Create task
- Assign member
- change status
- Dashboard summaries
### Payments
- Create shared payments 
- Split paymnet between members 
- Mark dept as paid
- Payment summaries 
### Dashboard
- Task statistics
- Upcoming deadlines
- Payment overview

## Main Endpoints
| | Method | Endpoint |
|---|---|---|
| Auth | POST | /auth/register |
| | POST | /auth/login |
| Groups | POST | /groups |
| | GET | /groups/my |
| | GET | /groups/{id}/members |
| Tasks | POST | /tasks |
| | GET | /tasks |
| | PATCH | /tasks/{id}/status |
| Payments | POST | /payments |
| | GET | /payments/my |
| | PATCH | /payments/share/{id}/pay |

## Roles 
#### ADMIN
Can:
- Add members
- Change Roles
- Manage group

#### MEMBER 
Can:
- Create tasks
- Update task status
- View dashboard
- Manage payments

## Security
- JWT authenticaton
- Passsword hashing using BCrypt
- Role checks through Membership system
## Fututre Improvements

- Role based access
- Better Error handling and validation
- Do not use user id
- Invite system email and code
- Notification
- History and progress
- Smart summaries
- State Management
- Cashing
- Security
