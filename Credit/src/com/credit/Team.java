package com.credit;
class Team{
		String name;
		String[] members;
		public Team(String name,String[] member){
			this.name=name;
			members=new String[member.length];
			for(int i=0;i<members.length;i++){
				members[i]=member[i];
			}
		}
		public String toJson(){
			String result="";
			result+="{\"name\":\"";
			result+=this.name;
			result+="\",";
			result+="\"members\":[";
			for(int i=0;i<this.members.length;i++){
				result+="\""+members[i]+"\"";
				if(i!=this.members.length-1){
					result+=",";
				}
			}
			result+="]}";
			
			return result;
		}
		
	}