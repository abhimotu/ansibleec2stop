while read line; do aws ec2 describe-instances --filter Name=private-ip-address,Values=$line --query 'Reservations[].Instances[].InstanceId' --output text ; done < serverlist.yaml | tr '\n' ',' | awk 'gsub(/\,$/,X) 1' >>output.txt