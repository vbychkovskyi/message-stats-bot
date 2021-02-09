CREATE DATABASE message_stats;
CREATE USER message_stats_user WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE message_stats TO message_stats_user;
