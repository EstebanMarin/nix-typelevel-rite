CREATE TABLE job_info (
    id UUID PRIMARY KEY,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    status TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

INSERT INTO
    job_info (
        id,
        name,
        description,
        status,
        created_at,
        updated_at
    )
VALUES
    (
        'd290f1ee-6c54-4b01-90e6-d701748f0851',
        'job1',
        'job1 description',
        'CREATED',
        '2021-01-01 00:00:00',
        '2021-01-01 00:00:00'
    ),
    (
        'd290f1ee-6c54-4b01-90e6-d701748f0852',
        'job1',
        'job1 description',
        'CREATED',
        '2021-01-01 00:00:00',
        '2021-01-01 00:00:00'
    );