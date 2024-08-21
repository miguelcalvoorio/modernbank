import uuid
import random
import json
from faker import Faker
from datetime import datetime, timedelta

fake = Faker()

def generate_uuid():
    return str(uuid.uuid4())

def generate_name():
    return fake.first_name()

def generate_last_name():
    return fake.last_name()

def generate_gender():
    return random.choice(['F', 'M'])

def generate_date_of_birth():
    start_date = datetime(1960, 1, 1)
    end_date = datetime(2000, 12, 31)
    return fake.date_time_between(start_date=start_date, end_date=end_date).strftime('%Y-%m-%dT%H:%M:%S.%fZ')

def generate_place_of_birth():
    return fake.city()

def generate_nationality():
    return random.choices(['ES', 'UK', 'FR'], weights=[0.7, 0.15, 0.15])[0]

def generate_status():
    return random.choices(['ACTIVE', 'INACTIVE', 'SUSPENDED'], weights=[0.8, 0.15, 0.05])[0]

def generate_created_date(dob):
    start_date = datetime.strptime(dob, '%Y-%m-%dT%H:%M:%S.%fZ')
    return fake.date_time_between(start_date=start_date, end_date=datetime.now()).strftime('%Y-%m-%dT%H:%M:%S.%fZ')

def generate_last_updated_date(created_date):
    created_date_dt = datetime.strptime(created_date, '%Y-%m-%dT%H:%M:%S.%fZ')
    return fake.date_time_between(start_date=created_date_dt, end_date=datetime.now()).strftime('%Y-%m-%dT%H:%M:%S.%fZ')

def generate_identifications(dob, nationality):
    id_type = random.choice(["NATIONAL_ID", "PASSPORT"])
    if id_type == "NATIONAL_ID":
        document_id = f"{random.randint(10000000, 99999999)}{random.choice('ABCDEFGHIJKLMNOPQRSTUVWXYZ')}"
    else:
        document_id = f"{fake.random_uppercase_letter()}{fake.random_uppercase_letter()}{fake.random_uppercase_letter()}{random.randint(100000, 999999)}"
    
    start_date = datetime.strptime(dob, '%Y-%m-%dT%H:%M:%S.%fZ')
    issue_date = fake.date_time_between(start_date=start_date, end_date=datetime.now()).strftime('%Y-%m-%dT%H:%M:%S.%fZ')

    start_date = datetime.strptime(issue_date, '%Y-%m-%dT%H:%M:%S.%fZ')
    expiration_date = fake.date_time_between(start_date=start_date, end_date=datetime.now()).strftime('%Y-%m-%dT%H:%M:%S.%fZ')
    
    return {
        "uuid": generate_uuid(),
        "type": id_type,
        "documentId": document_id,
        "issueDate": issue_date,
        "expirationDate": expiration_date,
        "issuer": nationality
    }

def generate_contacts():
    email_contact = {
        "uuid": generate_uuid(),
        "preferred": True,
        "type": "EMAIL",
        "contact": fake.email(),
        "roles": ["DEFAULT"]
    }
    phone_contact = {
        "uuid": generate_uuid(),
        "preferred": False,
        "type": "PHONE",
        "contact": f"+{random.randint(1, 99)}-{random.randint(100000000, 999999999)}",
        "roles": ["DEFAULT"]
    }
    return [email_contact, phone_contact]

def generate_addresses(nationality):
    return {
        "uuid": generate_uuid(),
        "address": fake.street_address(),
        "city": fake.city(),
        "state": fake.state(),
        "postalCode": f"{random.randint(10000, 99999)}",
        "country": nationality,
        "roles": ["DEFAULT"]
    }

def modified_by(modified_date):
    return {
      "uuid": "50627024-5110-49f7-955e-4e8e8aadbc03",
      "fullname": "John Doe",
      "timestamp": modified_date
    }

def generate_roles():
    return random.choices(['NO_CLIENT', 'CLIENT', 'AUTHORIZED_REPRESENTATIVE', 'BENEFICIARY'], weights=[0.2, 0.65, 0.1, 0.05])[0]

def generate_person():
    dob = generate_date_of_birth()
    nationality = generate_nationality()
    created_date = generate_created_date(dob)
    last_updated_date = generate_last_updated_date(created_date)
    
    person = {
        "uuid": generate_uuid(),
        "name": generate_name(),
        "lastName": generate_last_name(),
        "gender": generate_gender(),
        "dateOfBirth": dob,
        "placeOfBirth": generate_place_of_birth(),
        "nationality": nationality,
        "status": generate_status(),
        "roles": [generate_roles()],
        "createdBy": modified_by(created_date),
        "lastUpdatedBy": modified_by(last_updated_date),
        "identifications": [generate_identifications(dob, nationality)],
        "contacts": generate_contacts(),
        "addresses": [generate_addresses(nationality)]
    }
    
    return person

# Generate multiple records
def generate_multiple_persons(num_records):
    persons = []
    for _ in range(num_records):
        persons.append(generate_person())
    return persons

# Generate 10 sample records
num_records = 1000
persons_data = generate_multiple_persons(num_records)

# Print the generated data in JSON format
print(json.dumps(persons_data, indent=4))