variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }

variable "name" { default = "JenkinsDebianServer" }

variable "network" { default = "c23097744_network" }  

variable "keypair" { default = "c23097744_os_keypair" }
variable "pool" { default = "cscloud_private_floating" }
variable "server_script" { default = "./myJenkinsServer.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tmptf_security" }
