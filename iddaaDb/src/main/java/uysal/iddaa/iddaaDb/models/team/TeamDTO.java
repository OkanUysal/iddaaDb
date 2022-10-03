package uysal.iddaa.iddaaDb.models.team;

public class TeamDTO {

	private String name;
    private Long id;

    public TeamDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
}
