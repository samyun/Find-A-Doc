package findadoc;

public enum TheEnum {
    Pain, Sneeze, Sleep, Emotion, 
    Tooth, Vision 
}

public class EnumTest {
    Type symptom;

    public EnumTest(Type symptom) {
        this.symptom = symptom;
    }

    public void tellItLikeItIs() {

        switch (symptom) {
            case Pain: 
                getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
                break;

            case Sneeze: 
                getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
                break;

            case Sleep:
            	getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
                break;
                
            case Emotion: 
                getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
                break;

            case Tooth:
            	getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
            	break;
            
            case Vision:
            	getadoc("takes the symptom based on the case,reads the docotr info from the areas.put");
            	break;
        }
    }
} 