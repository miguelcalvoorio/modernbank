db = db.getSiblingDB('modernbank')
db.createUser({
    user: 'party-admin',
    pwd:  'party-password',
    roles: [
        {
            role: 'readWrite',
            db: 'modernbank'
        }
    ]
})
db.createCollection('parties');