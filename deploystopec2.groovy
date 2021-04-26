#!/usr/bin/env groovy
pipeline {
   agent any

   stages {
      stage('Get Instance ID from AWS Private IPs') {
        steps {
          sh """#!/bin/bash
          echo 'Retreiving...'
          sh getInsID.sh
        """
        }
   }
   stage('Power Down EC2') {
     steps {
        sh """#!/bin/bash
        echo 'Powering Down EC2 $output.txt ...'
        ansible-vault decrypt secret-vars.yml --vault-password-file=pass.txt
        ansible-playbook stopec2ansible -e secret-vars.yml
       """ 
    }
   }
  }
}
