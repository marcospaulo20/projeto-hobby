package ngprojetohobby.manga.services;

import java.util.List;

import ngprojetohobby.manga.domain.Volume;

public interface VolumeService {

	List<Volume> getAllVolumes(Long id);

    Volume getById(Long id);

    Volume createOrUpdateVolume(Volume volume);

    Volume update(Volume volume);

    void remove(Long id);

    Integer getNumberOfVolumes();
    
}
