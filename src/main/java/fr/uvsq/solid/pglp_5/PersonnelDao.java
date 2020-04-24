package fr.uvsq.solid.pglp_5;

public class PersonnelDao extends DAO<Personnels>
{

	SerialisationFactory Factory=new SerialisationFactory();
	private Flash log=new Flash();
	private String filename="crud.save";
	
	@Override
	public Personnels create (Personnels obj) 
	{	
		return Factory.writeFile(obj, filename, log);
	}

	@Override
	public Personnels find(int id) {
		
		return Factory.readFile(this.filename,this.log,id);
	}

	@Override
	public void update(int id) {
		
		 Factory.Update(id, filename, log);
	}

	@Override
	public void delete(Personnels obj) {
		
		Factory.readFile(this.filename, this.log, obj.getid());
		
	}

}
