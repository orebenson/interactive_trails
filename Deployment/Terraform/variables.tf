variable "flavor" { default = "m1.large" }
variable "image" { default = "Debian Buster 10.11.1 20211029" }
variable "name1" { default = "DebianServer" }

variable "keypair" { default = "c23097744_os_keypair" } 
variable "network" { default = "c23097744_network" }  
variable "pool" { default = "cscloud_private_floating" }
variable "server1_script" { default = "./server1.sh" }
variable "security_description" { default = "Terraform security group" }
variable "security_name" { default = "tf_security" }
