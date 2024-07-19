Report Generator:

Consider you are getting different set of feed files in csv format from an upstream source. For each set of feeds you need to generate a report  based on certain rules and reference data to lookup from reference files. There can be different set of feed files with each set containing the input.csv.
The report generation may be scheduled and the schedule can change in future. Create a java spring boot service that is able to ingest these files and create transformed reports in scheduled time.
One should also be able to trigger the report generation via Rest API.
