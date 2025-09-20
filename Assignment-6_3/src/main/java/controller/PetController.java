package controller;



import model.Pet;

public class PetController {
    private final Pet pet;
    // Movement tuning
    private final double speedPixelsPerSec;     // max speed
    private final double arrivalRadius;         // stop threshold in px

    // Target; null means no active target (e.g., mouse left the canvas)
    private Double targetX = null;
    private Double targetY = null;

    public PetController(Pet pet, double speedPixelsPerSec, double arrivalRadius) {
        this.pet = pet;
        this.speedPixelsPerSec = speedPixelsPerSec;
        this.arrivalRadius = arrivalRadius;
    }

    public void setTarget(double x, double y) {
        this.targetX = x;
        this.targetY = y;
    }

    public void clearTarget() {
        this.targetX = null;
        this.targetY = null;
    }

    public boolean hasTarget() {
        return targetX != null && targetY != null;
    }

    /**
     * Move the pet toward the current target using a capped speed.
     * @param dtSeconds time since last frame (seconds)
     * @param canvasW canvas width for clamping
     * @param canvasH canvas height for clamping
     */
    public void update(double dtSeconds, double canvasW, double canvasH) {
        if (!hasTarget()) return;

        double px = pet.getX();
        double py = pet.getY();
        double cx = targetX;
        double cy = targetY;

        double dx = cx - px;
        double dy = cy - py;
        double dist = Math.hypot(dx, dy);

        // Arrived?
        if (dist <= arrivalRadius) {
            pet.setPosition(cx, cy);
            clearTarget();
            return;
        }

        // Normalize direction and step with capped speed
        if (dist > 0) {
            double vx = dx / dist;
            double vy = dy / dist;

            double step = speedPixelsPerSec * dtSeconds;
            if (step > dist) step = dist; // don't overshoot

            double nx = px + vx * step;
            double ny = py + vy * step;

            // Clamp so the whole image stays inside canvas bounds
            double halfW = pet.getWidth() / 2.0;
            double halfH = pet.getHeight() / 2.0;
            nx = Math.max(halfW, Math.min(canvasW - halfW, nx));
            ny = Math.max(halfH, Math.min(canvasH - halfH, ny));

            pet.setPosition(nx, ny);
        }
    }

    public Pet getPet() {
        return pet;
    }
}


