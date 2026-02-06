-- Seed Data for Auth Service
-- User: test-customer@bank.com
-- Password: postgres (BCrypt: $2a$10$PwJ3REG5VVvTXSabmaQLV.ejY627ug995c7W1hvtzCnA/b6Kv9FXW)

-- We use customer_id 1001 to avoid conflicts with existing ID 1
INSERT INTO user_auth (
    customer_id, 
    email, 
    password_hash, 
    account_status, 
    role, 
    created_at, 
    updated_at
) VALUES (
    1001, 
    'test-customer@bank.com', 
    '$2a$10$PwJ3REG5VVvTXSabmaQLV.ejY627ug995c7W1hvtzCnA/b6Kv9FXW', 
    'ACTIVE', 
    'CUSTOMER', 
    NOW(), 
    NOW()
) ON CONFLICT (email) DO NOTHING;
