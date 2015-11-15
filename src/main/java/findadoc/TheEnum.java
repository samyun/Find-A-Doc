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
                System.out.println("Mondays are bad.");
                break;

            case Sneeze: 
                System.out.println("Fridays are better.");
                break;

            case Sleep:
            	System.out.println("Fridays are better.");
                break;
                
            case Emotion: 
                System.out.println("Weekends are best.");
                break;

            case Tooth:
            	System.out.println("garbage here.");
            	break;
            
            case Vision:
            	System.out.println("more garbage.");
            	break;
        }
    }
} 