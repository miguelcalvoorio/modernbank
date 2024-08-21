import { BasicDictionary } from "../../shared/models";

export const dataGenderDictionary: BasicDictionary = Object.create({ title: 'People gender dictionary' });
dataGenderDictionary["M"] = "Male";
dataGenderDictionary["F"] = "Female";
dataGenderDictionary["U"] = "Not specified";

export const dataStatusDictionary: BasicDictionary = Object.create({ title: 'People status dictionary' });
dataStatusDictionary["ACTIVE"] = "Active";
dataStatusDictionary["UNVERIFIED"] = "Unverified";
dataStatusDictionary["LIMITED"] = "Access limited";
dataStatusDictionary["INACTIVE"] = "Inactive";
dataStatusDictionary["SUSPENDED"] = "Suspended";
dataStatusDictionary["CLOSED"] = "Closed";

export const dataRolesDictionary: BasicDictionary = Object.create({ title: 'People roles dictionary' });
dataRolesDictionary["DEFAULT"] = "By default";
dataRolesDictionary["NO_CLIENT"] = "No client";
dataRolesDictionary["CLIENT"] = "Individual";
dataRolesDictionary["AUTHORIZED_REPRESENTATIVE"] = "Authorized representative";
dataRolesDictionary["BENEFICIARY"] = "Beneficiary";